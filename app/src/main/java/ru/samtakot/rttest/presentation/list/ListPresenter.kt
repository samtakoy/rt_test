package ru.samtakot.rttest.presentation.list

import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ListPresenter : MvpPresenter<ListView>(){


    class Factory @Inject constructor(){
        fun create(
        ) = ListPresenter()
    }

    init{

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}