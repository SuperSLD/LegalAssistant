package com.example.legalassistant.ui.consultation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView
import com.example.legalassistant.base.BasePresenter
import com.example.legalassistant.common.CiceroneHolder
import com.example.legalassistant.extentions.getMessageModels
import com.example.legalassistant.extentions.saveMessage
import com.example.legalassistant.extentions.toMessageHuman
import com.example.legalassistant.extentions.toMessageRealmModel
import com.example.legalassistant.models.db.MessageRealmModel
import com.example.legalassistant.models.human.MessageHuman
import com.example.legalassistant.server.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmResults
import org.koin.core.inject
import ru.terrakok.cicerone.Router
import timber.log.Timber

@InjectViewState
class ConsultationPresenter: BasePresenter<ConsultationView>() {

    private val realm: Realm by inject()
    private val service: ApiService by inject()
    private val navigationHolder: CiceroneHolder by inject()
    private val router: Router?
        get() = navigationHolder.currentRouter

    override fun attachView(view: ConsultationView?) {
        super.attachView(view)
        loadMessages()
    }
    fun back() = router?.exit()

    fun sendMessage(text: String) {
        service.getMessage(text)
            .map { if (it.success == true) it else error(it.toString()) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                {
                    if (it.response_text != null) viewState.showNewMessage(it.response_text, true)
                },
                {
                    Timber.e(it)
                })
    }

    private fun loadMessages() {
        viewState.loadFromRealm(realm.getMessageModels().toMessageHuman())
    }

    fun addToRealm(id: Int, text: String, date: String, incoming: Boolean) {
        realm.saveMessage(MessageHuman(text, date, incoming).toMessageRealmModel(id))
    }
}