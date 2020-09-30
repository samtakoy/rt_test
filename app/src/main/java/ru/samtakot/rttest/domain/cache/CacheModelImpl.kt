package ru.samtakot.rttest.domain.cache

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import ru.samtakot.rttest.R
import ru.samtakot.rttest.domain.Locals
import ru.samtakot.rttest.domain.TimestampHolder
import ru.samtakot.rttest.domain.dto.UserPage
import ru.samtakot.rttest.domain.entity.User
import ru.samtakot.rttest.domain.reps.RemoteUserRepository
import ru.samtakot.rttest.domain.reps.UserCacheRepository
import ru.samtakot.rttest.extensions.io
import ru.samtakot.rttest.extensions.ioToMain
import javax.inject.Inject

private const val TAG = "CacheModelImpl"

class CacheModelImpl @Inject constructor(

    cacheSettings: CacheSettings,
    val cacheRepository: UserCacheRepository,
    val remoteRepository: RemoteUserRepository,
    locals: Locals,
    val timestampHolder: TimestampHolder
    ) : CacheModel {

    private var cacheStatus = CacheStatus.NOT_INITIALIZED
    private var networkBusyStatus: BehaviorSubject<Boolean> = BehaviorSubject.create()
    private var errors: BehaviorSubject<CacheError> = BehaviorSubject.create()
    private val cacheValidator = CacheValidator(cacheSettings.expireIntervalSeconds, locals)

    private val currentRequests: CompositeDisposable = CompositeDisposable()

    override fun init() {
        if(cacheStatus == CacheStatus.NOT_INITIALIZED){
            networkBusyStatus.onNext(false)
            cacheValidator.validateByTimestamp(timestampHolder.timestampSeconds)
            retrieveInitialData()
        }
    }

    private fun retrieveInitialData() {

        if(cacheValidator.isSynchronized()){
            changeCacheStatus(CacheStatus.SYNCHRONIZED)
        } else {
            changeCacheStatus(CacheStatus.UNCOMPLETED)
            retrieveMoreEmployees()
        }
    }

    override fun clear() {

        if(cacheStatus.isNetworkBusy){
            errors.onNext(CacheError(R.string.cache_err_loading_in_progress))
            return
        }

        val prevStatus = cacheStatus
        changeCacheStatus(CacheStatus.DATA_RETRIEVING)

        cacheValidator.invalidate()

        currentRequests.clear()
        currentRequests.add(
            cacheRepository.clearUsers()
                .io()
                .subscribe(
                    {
                        changeCacheStatus(prevStatus)
                        retrieveMoreEmployees()
                    },
                    {throwable ->
                        changeCacheStatus(prevStatus)
                        errors.onNext(CacheError(R.string.cache_err_cant_clear))
                    }
                )
        )
    }

    override fun observeNetworkBusyStatus(): Observable<Boolean>{
        return networkBusyStatus.distinct()
    }

    override fun observeErrors(): Observable<CacheError> {
        return errors
    }

    override fun observeUsers(): Flowable<List<User>> = cacheRepository.getUsers().io()

    private fun changeCacheStatus(newStatus: CacheStatus){

        if(cacheStatus != newStatus) {
            Log.d(TAG, "* changeCacheStatus:${newStatus.name}")
            cacheStatus = newStatus
            networkBusyStatus.onNext(newStatus.isNetworkBusy)
        }
    }

    override fun retrieveMoreEmployees() {

        if(cacheStatus != CacheStatus.UNCOMPLETED){
            return
        }


        changeCacheStatus(CacheStatus.DATA_RETRIEVING)

        val pageNum = if (cacheValidator.hasCacheRecord) cacheValidator.pagesLoaded+1 else 1

        Log.d(TAG, "*** data retrieving, pageNum:${pageNum}")

        currentRequests.clear()
        currentRequests.add(
            remoteRepository.retrieveMoreEmployees(pageNum)
                .io()
                .subscribe(
                    {userPage -> onRetrieveEmployeesComplete(userPage!!)},
                    {throwable -> onRetrieveEmployeesError(throwable)}
                )
        )
    }

    private fun onRetrieveEmployeesComplete(resultPage: UserPage) {

        Log.d(TAG, "*** page loaded, ${resultPage.page}/${resultPage.totalPages}")

        if(!cacheValidator.verifyPageNumbers(resultPage.page, resultPage.totalPages)){
            cacheValidator.invalidate()
        }

        if(resultPage.page > 1 && !cacheValidator.hasCacheRecord){
            // все перезапросить заново
            retrieveInitialData()
            return
        }

        currentRequests.clear()
        currentRequests.add(
            observeUsersAdding(resultPage.users, !cacheValidator.hasCacheRecord)
                .io()
                .subscribe(
                    {
                        cacheValidator.onNewData(resultPage.page, resultPage.totalPages, timestampHolder.timestampSeconds)
                        changeCacheStatus(if(resultPage!!.isLast()) CacheStatus.SYNCHRONIZED else CacheStatus.UNCOMPLETED)
                    },
                    {throwable ->
                        cacheValidator.invalidate()
                        onRetrieveEmployeesHandleError(throwable)
                    }
                )
        )
    }

    private fun observeUsersAdding(users: List<User>, andClearBefore:Boolean): Completable{
        return if(andClearBefore)
            cacheRepository
                    .clearUsers()
                    .andThen(cacheRepository.addData(users))
            else
            cacheRepository.addData(users)
    }

    private fun onRetrieveEmployeesHandleError(throwable: Throwable) {
        Log.e(TAG, "onRetrieveEmployeesError", throwable)

        errors.onNext(CacheError(R.string.cache_err_cant_handle_request))
        changeCacheStatus(CacheStatus.UNCOMPLETED)
    }

    private fun onRetrieveEmployeesError(throwable: Throwable) {
        Log.e(TAG, "onRetrieveEmployeesError", throwable)

        errors.onNext(CacheError(R.string.cache_err_cant_reuest))
        changeCacheStatus(CacheStatus.UNCOMPLETED)
    }
}