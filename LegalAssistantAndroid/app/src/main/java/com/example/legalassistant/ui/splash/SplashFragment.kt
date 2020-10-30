package com.example.legalassistant.ui.splash

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.legalassistant.R
import com.example.legalassistant.base.BaseFragment

class SplashFragment : BaseFragment(R.layout.fragment_splash), MvpView {
    @InjectPresenter
    lateinit var presenter: SplashPresenter

    override fun onBackPressed() {
        presenter.back()
    }
}