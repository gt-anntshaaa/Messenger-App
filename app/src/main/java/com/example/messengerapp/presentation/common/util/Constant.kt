package com.example.messengerapp.presentation.common.util

import com.example.messengerapp.R

object Constant {
    const val KEY_LAYOUT = "layout_key"
    const val KEY_TOOLBAR = "toolbar_key"
    const val KEY_NAV_HOST = "nav_host_key"

    val ROOT_DESTINATIONS = setOf(R.id.signInFragment)

    enum class REFERENCE{
        USER,
        CONTACT,
        IMAGES
    }
}