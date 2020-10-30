package com.example.legalassistant.ui

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.legalassistant.R
import com.example.legalassistant.Screens
import com.example.legalassistant.base.BaseFragment
import com.example.legalassistant.extentions.doOnApplyWindowInsets
import com.example.legalassistant.extentions.hideKeyboard
import com.example.legalassistant.extentions.updatePadding
import kotlinx.android.synthetic.main.layout_container.*

class AppActivity : MvpAppCompatActivity() {

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)
        setTheme(R.style.AppTheme)
        initTransparent()
        initInsets()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, Screens.FlowGlobal.fragment)
                .commitNow()
        }
    }

    private fun initTransparent() {
        window.apply {
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                decorView.systemUiVisibility = decorView.systemUiVisibility or
                        View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            }

            statusBarColor = ContextCompat.getColor(context, R.color.colorNavigationBar)
            navigationBarColor = ContextCompat.getColor(context, R.color.colorNavigationBar)
        }
    }

    private fun initInsets() {
        container.doOnApplyWindowInsets { view, insets, initialPadding ->
            view.updatePadding(
                left = initialPadding.left + insets.systemWindowInsetLeft,
                right = initialPadding.right + insets.systemWindowInsetRight
            )
            insets.replaceSystemWindowInsets(
                Rect(
                    0,
                    insets.systemWindowInsetTop,
                    0,
                    insets.systemWindowInsetBottom
                )
            )
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    hideKeyboard()
                    v.clearFocus()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }
}