package ru.samtakot.rttest.presentation.list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.samtakot.rttest.domain.cache.CacheModel
import javax.inject.Inject

@InjectViewState
class ListPresenter(
    val cache: CacheModel
) : MvpPresenter<ListView>(){


    class Factory @Inject constructor(
        val cache: CacheModel
    ){
        fun create(
        ) = ListPresenter(cache)
    }

    private val observers: CompositeDisposable = CompositeDisposable()

    init{
        cache.checkForInitialization()
        observeUpdates()
    }

    override fun onDestroy() {


        observers.dispose()

        super.onDestroy()
    }

    private fun observeUpdates() {

        observers.add(
            cache.observeNetworkBusyStatus()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if(it) viewState.showDataLoading() else viewState.hideDataLoading()
                }
        )

        observers.add(
            cache.observeErrors()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    viewState.showMessage(it.errorTextId)
                }
        )

        observers.add(
            cache.observeUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    viewState.setData(it)
                }
        )

    }

    fun onUiGetMoreUsers() {
        cache.retrieveMoreUsers()
    }

    fun onUserItemClick(userId: Int) {
        viewState.navigateToUser(userId)
    }

    fun onUiSettingsClick() {
        viewState.navigateToSettings()
    }

    fun onUiCheckCacheStatus(){
        cache.checkForInitialization()
    }

}