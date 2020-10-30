package com.example.legalassistant.ui.main

import android.os.Bundle
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.legalassistant.R
import com.example.legalassistant.base.BaseFragment

class MainFragment : BaseFragment(R.layout.fragment_main), MvpView {
    @InjectPresenter
    lateinit var presenter: MainPresenter
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onBackPressed() {
        presenter.back()
    }
}