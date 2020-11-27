package com.orangesoft.addressbook.view.extensions

import android.view.View

fun View.setVisible(visible: Boolean = true) {
    if (visible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}