package com.example.legalassistant.ui.splash

import android.content.Context
import com.arellomobile.mvp.MvpView
import com.example.legalassistant.Screens
import com.example.legalassistant.base.BasePresenter
import com.example.legalassistant.common.CiceroneHolder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.inject
import ru.terrakok.cicerone.Router
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SplashPresenter : BasePresenter<MvpView>() {
    private val navigationHolder: CiceroneHolder by inject()
    private val context : Context by inject()

    private val router: Router?
        get() = navigationHolder.currentRouter

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        start()
    }

    private fun start() {
        Single
            .timer(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    router?.newRootScreen(Screens.FlowContainer)
                },
                {
                    Timber.e(it)
                }
            ).connect()
    }

    fun back() {
        router?.exit()
    }
}