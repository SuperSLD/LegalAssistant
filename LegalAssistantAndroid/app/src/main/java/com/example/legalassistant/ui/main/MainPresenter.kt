package com.example.legalassistant.ui.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView
import com.example.legalassistant.base.BasePresenter
import com.example.legalassistant.common.CiceroneHolder
import org.koin.core.inject
import ru.terrakok.cicerone.Router

@InjectViewState
class MainPresenter : BasePresenter<MvpView>() {

    private val navigationHolder: CiceroneHolder by inject()
    private val router: Router?
        get() = navigationHolder.currentRouter

    fun back() = router?.exit()
}