package com.example.legalassistant.ui.ready_solutions

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView
import com.example.legalassistant.Screens
import com.example.legalassistant.base.BasePresenter
import com.example.legalassistant.common.CiceroneHolder
import com.example.legalassistant.extentions.toSolutionsHuman
import com.example.legalassistant.models.human.SolutionHuman
import com.example.legalassistant.server.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.inject
import ru.terrakok.cicerone.Router
import timber.log.Timber

@InjectViewState
class ReadySolutPresenter : BasePresenter<ReadySolutView>() {

    private val service: ApiService by inject()
    private val navigationHolder: CiceroneHolder by inject()
    private val router: Router?
        get() = navigationHolder.currentRouter

    override fun attachView(view: ReadySolutView?) {
        super.attachView(view)
        getList()
    }

    fun back() = router?.exit()

    fun onSolutionClick(solutionHuman: SolutionHuman) {
        router?.navigateTo(Screens.SolutionInfo(solutionHuman))
    }

    private fun getList() {
        service.getList()
            .map { it.list}
            .map { it.toSolutionsHuman() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                {
                    viewState.showSolutions(it)
                },
                {
                    Timber.e(it)
                })
    }

}