package ru.samtakot.rttest.presentation.details

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.samtakot.rttest.domain.entity.User

interface DetailsView : MvpView{

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showUser(user: User)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(errorId: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateToolbarTitle(title: String)
}