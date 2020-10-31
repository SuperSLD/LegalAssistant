package com.example.legalassistant.ui.consultation

import android.os.Bundle
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.legalassistant.R
import com.example.legalassistant.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_consultation.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class ConsultationFragment : BaseFragment(R.layout.fragment_consultation), ConsultationView {

    @InjectPresenter
    lateinit var presenter: ConsultationPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(include_toolbar) {
            tvTitle.text = resources.getString(R.string.consultation)
            icClose.setOnClickListener { onBackPressed() }
        }
    }

    override fun onBackPressed() {
        presenter.back()
    }
}