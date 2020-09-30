package ru.samtakot.rttest.presentation.details

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.samtakot.rttest.R
import ru.samtakot.rttest.domain.reps.UserCacheRepository
import ru.samtakot.rttest.extensions.ioToMain
import javax.inject.Inject

private const val TAG = "DetailsPresenter"

@InjectViewState
class DetailsPresenter(
    val userCacheRepository: UserCacheRepository,
    val userId: Int
) : MvpPresenter<DetailsView>(){

    class Factory @Inject constructor(
        val userCacheRepository: UserCacheRepository
    ){
        fun create(
            userId: Int

        ) = DetailsPresenter(userCacheRepository, userId)
    }

    private var disposables = CompositeDisposable()

    init{
        viewState.updateToolbarTitle("")
        observeUser()
    }

    private fun observeUser() {
        disposables.clear()
        disposables.add(
            userCacheRepository.getUser(userId)
                .ioToMain()
                .subscribe(
                    {
                        viewState.showUser(it)
                        viewState.updateToolbarTitle(it.firstName)
                    },
                    {throwable-> onGetUserError(throwable)}
                )
        )
    }

    private fun onGetUserError(throwable: Throwable?) {
        Log.e(TAG, "Error on user from db getting", throwable)
        viewState.showError(R.string.card_db_error)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}