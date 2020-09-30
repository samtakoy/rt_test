package ru.samtakot.rttest.presentation.list

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.samtakot.rttest.domain.entity.User

interface ListView : MvpView{

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(messageId: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setData(data: List<User>)

    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = "loading")
    fun showDataLoading()

    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = "loading")
    fun hideDataLoading()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToUser(userId: Int)

}