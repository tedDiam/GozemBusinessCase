package com.sergediame.gozembusinesscase.auth.login

import com.sergediame.gozembusinesscase.common.UiModel

data class UiLogin(
    val loggedIn: Boolean
) : UiModel {
    companion object {
        val INITIAL = UiLogin(loggedIn = false)
    }
}


internal fun Boolean.toUi() = UiLogin(loggedIn = this)