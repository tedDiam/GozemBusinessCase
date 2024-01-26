package com.sergediame.gozembusinesscase.auth.register

import com.sergediame.domain.form.EmailAddressField
import com.sergediame.domain.form.PasswordField
import com.sergediame.gozembusinesscase.common.UiEvent

/**
 * Registration Screen Events
 */
sealed class RegistrationUiEvent: UiEvent {
    data class EmailChanged(val input: EmailAddressField) : RegistrationUiEvent()
    data class PasswordChanged(val input: PasswordField) : RegistrationUiEvent()
    data class ConfirmPasswordChanged(val input: PasswordField ) : RegistrationUiEvent()
    data object Submit : RegistrationUiEvent()
}