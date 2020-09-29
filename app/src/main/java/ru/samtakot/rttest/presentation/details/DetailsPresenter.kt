package ru.samtakot.rttest.presentation.details

import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class DetailsPresenter : MvpPresenter<DetailsView>(){


    class Factory @Inject constructor(){
        fun create(
        ) = DetailsPresenter()
    }

    init{

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}