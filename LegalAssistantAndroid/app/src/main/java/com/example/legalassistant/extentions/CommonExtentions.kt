package com.example.legalassistant.extentions

import android.app.Activity
import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*


fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

fun Context.hideKeyboard(view: View?) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Context.getData() : String {
    val data = Calendar.getInstance().time
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return formatter.format(data)
}