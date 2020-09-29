package ru.samtakot.rttest.presentation.list

import android.os.Bundle
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class ListFragment : MvpAppCompatFragment(), ListView{

    @Inject
    lateinit var factoryProvider: Provider<ListPresenter.Factory>
    private val presenter by moxyPresenter { factoryProvider.get().create() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}