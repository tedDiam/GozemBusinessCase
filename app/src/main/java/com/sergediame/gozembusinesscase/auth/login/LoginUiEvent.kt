package com.sergediame.gozembusinesscase.auth.login

import com.sergediame.domain.form.EmailAddressField
import com.sergediame.gozembusinesscase.common.UiEvent
import com.sergediame.domain.form.PasswordField

sealed class LoginUiEvent: UiEvent {
    data class EmailOrMobileChanged(val input: EmailAddressField) : LoginUiEvent()
    data class PasswordChanged(val input: PasswordField) : LoginUiEvent()
    data object Submit : LoginUiEvent()
}