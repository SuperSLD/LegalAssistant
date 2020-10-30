package com.example.legalassistant

import androidx.fragment.app.Fragment
import com.example.legalassistant.ui.container.FlowContainerFragment
import com.example.legalassistant.ui.global.FlowGlobalFragment
import com.example.legalassistant.ui.main.MainFragment
import com.example.legalassistant.ui.splash.SplashFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    const val APP_ROUTER = "APP_ROUTER"

    object FlowGlobal : SupportAppScreen() {
        override fun getFragment() = FlowGlobalFragment()
    }

    object Splash : SupportAppScreen() {
        override fun getFragment() = SplashFragment()
    }

    const val CONTAINER_ROUTER = "CONTAINER_ROUTER"

    object FlowContainer : SupportAppScreen() {
        override fun getFragment() = FlowContainerFragment()
    }
    object MainFragment : SupportAppScreen() {
        override fun getFragment() = MainFragment()
    }
}