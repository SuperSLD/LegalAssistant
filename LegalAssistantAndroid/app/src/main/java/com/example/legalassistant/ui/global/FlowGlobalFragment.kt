package com.example.legalassistant.ui.global

import android.os.Bundle
import com.arellomobile.mvp.MvpView
import com.example.legalassistant.Screens
import com.example.legalassistant.Screens.APP_ROUTER
import com.example.legalassistant.base.FlowFragment
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Replace

class FlowGlobalFragment : FlowFragment(APP_ROUTER), MvpView {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            navigator.applyCommands(
                arrayOf(
                    BackTo(null),
                    Replace(Screens.Splash)
                )
            )
        }
    }

}