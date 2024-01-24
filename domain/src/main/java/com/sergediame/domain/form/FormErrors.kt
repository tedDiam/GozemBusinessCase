package com.sergediame.domain.form

sealed class FormError {
    data class PasswordTooShort(val minLength: Int) : FormError()
    data object InvalidEmailAddress : FormError()
    data object InvalidPhoneNumber : FormError()
    data object InvalidPin: FormError()
    data object Required : FormError()
}
