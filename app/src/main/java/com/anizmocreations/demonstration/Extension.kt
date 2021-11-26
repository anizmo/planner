package com.anizmocreations.demonstration

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * This is an extension function of the context class, the method inside these require context as
 * a parameter but since it is an extension on the Context class itself it takes the reference of
 * the Context object on which this extension function by using the 'this' keyword is called and
 * executes it.
 */
fun Context.hideKeyboard(view: View) {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
