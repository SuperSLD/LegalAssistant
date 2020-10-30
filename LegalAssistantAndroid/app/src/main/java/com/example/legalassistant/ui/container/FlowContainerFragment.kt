package com.example.legalassistant.ui.container

import android.os.Bundle
import com.example.legalassistant.Screens
import com.example.legalassistant.Screens.CONTAINER_ROUTER
import com.example.legalassistant.base.FlowFragment
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Replace

class FlowContainerFragment : FlowFragment(CONTAINER_ROUTER) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            navigator.applyCommands(
                arrayOf(
                    BackTo(null),
                    Replace(Screens.MainFragment)
                )
            )
        }
    }
}