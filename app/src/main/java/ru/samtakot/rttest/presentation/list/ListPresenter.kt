package ru.samtakot.rttest.presentation.list

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

        cache.init()
        observeUpdates()
    }

    override fun onDestroy() {


        observers.dispose()

        super.onDestroy()
    }

    private fun observeUpdates() {

        observers.add(
            cache.observeNetworkBusyStatus()
                .subscribe {
                    if(it) viewState.showDataLoading() else viewState.hideDataLoading()
                }
        )

        observers.add(
            cache.observeErrors()
                .subscribe {
                    viewState.showMessage(it.errorTextId)
                }
        )

        observers.add(
            cache.observeUsers()
                .subscribe {
                    viewState.setData(it)
                }
        )

    }

    fun onUiGetMoreUsers() {
        cache.retrieveMoreEmployees()
    }
}