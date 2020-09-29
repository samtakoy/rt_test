package ru.samtakot.rttest.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.samtakot.rttest.R
import ru.samtakot.rttest.app.di.Di
import javax.inject.Inject
import javax.inject.Provider

class DetailsFragment : MvpAppCompatFragment(), DetailsView{

    @Inject
    lateinit var factoryProvider: Provider<DetailsPresenter.Factory>
    private val presenter by moxyPresenter { factoryProvider.get().create() }

    override fun onCreate(savedInstanceState: Bundle?) {

        Di.appComponent.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)
        return view
    }

}