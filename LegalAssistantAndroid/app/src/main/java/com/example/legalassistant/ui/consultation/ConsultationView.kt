package com.example.legalassistant.ui.consultation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.legalassistant.models.human.MessageHuman

interface ConsultationView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showNewMessage(text: String, incoming: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun loadFromRealm(messages: MutableList<MessageHuman>)
}