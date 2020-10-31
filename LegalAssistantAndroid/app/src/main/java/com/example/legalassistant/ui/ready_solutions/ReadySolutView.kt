package com.example.legalassistant.ui.ready_solutions

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.legalassistant.models.human.SolutionHuman

interface ReadySolutView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showSolutions(solutions: MutableList<SolutionHuman>)
}