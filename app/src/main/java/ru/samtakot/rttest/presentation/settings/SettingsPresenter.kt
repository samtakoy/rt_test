package ru.samtakot.rttest.presentation.settings

import android.util.Log
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.samtakot.rttest.R
import ru.samtakot.rttest.domain.cache.CacheModel
import javax.inject.Inject

private const val TAG = "SettingsPresenter"

@InjectViewState
class SettingsPresenter @Inject constructor(
    val cache: CacheModel
) : MvpPresenter<SettingsView>(){

    init{

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun subscribeToOperation(operation: Completable){
        operation
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.showMessage(R.string.msg_settings_apply_success)
                },
                {throwable->
                    Log.e(TAG, "msg_settings_apply_error", throwable)
                    viewState.showMessage(R.string.msg_settings_apply_error)
                }
            )
    }

    fun onUiInvalidateDbCache() {
        subscribeToOperation(cache.invalidateDbCache())
    }

    fun onUiClearDbCache() {
        subscribeToOperation(cache.clearDbCache())
    }

    fun onUiClearGlideCaches() {
        viewState.clearGlideCaches()
    }
}
