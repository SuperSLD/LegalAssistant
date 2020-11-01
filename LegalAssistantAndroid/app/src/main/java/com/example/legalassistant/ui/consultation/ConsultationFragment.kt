package com.example.legalassistant.ui.consultation

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.legalassistant.R
import com.example.legalassistant.base.BaseFragment
import com.example.legalassistant.extentions.getDate
import com.example.legalassistant.models.human.MessageHuman
import com.example.legalassistant.ui.ready_solutions.ReadySolutAdapter
import kotlinx.android.synthetic.main.fragment_consultation.*
import kotlinx.android.synthetic.main.fragment_ready_solutions.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class ConsultationFragment : BaseFragment(R.layout.fragment_consultation), ConsultationView {

    @InjectPresenter
    lateinit var presenter: ConsultationPresenter
    private lateinit var adapter : ConsultationAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(include_toolbar) {
            tvTitle.text = resources.getString(R.string.consultation)
            icClose.setOnClickListener { onBackPressed() }
        }

        adapter = ConsultationAdapter()

        with(listMessages) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ConsultationFragment.adapter
        }

        with(ivSendMessage) {
            setOnClickListener {
                presenter.sendMessage(etMessage.text.toString())
                adapter.addNewMessage(etMessage.text.toString(), false)
                presenter.addToRealm(adapter.itemCount - 1, etMessage.text.toString(), getDate(), false)
                etMessage.setText("")
                listMessages.smoothScrollToPosition(adapter.itemCount - 1)
            }
        }

    }

    override fun onBackPressed() {
        presenter.back()
    }

    override fun showNewMessage(text: String, incoming: Boolean) {
        adapter.addNewMessage(text, incoming)
        presenter.addToRealm(adapter.itemCount - 1, text, getDate(), true)
    }

    override fun loadFromRealm(messages: MutableList<MessageHuman>) {
        adapter.addAll(messages)
        listMessages.layoutManager?.scrollToPosition(adapter.itemCount - 1)
    }
}