package com.example.messengerapp.presentation.common.util

import android.content.Context
import android.view.View

fun View.show(){
    visibility = View.VISIBLE
    isClickable = false
    isFocusable = false
}

fun View.hide(){
    visibility = View.GONE
    isClickable = true
    isFocusable = true
}