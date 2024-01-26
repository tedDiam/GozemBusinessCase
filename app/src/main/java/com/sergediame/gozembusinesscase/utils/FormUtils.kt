package com.sergediame.gozembusinesscase.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sergediame.domain.form.FormError
import com.sergediame.gozembusinesscase.R


@Composable
fun getErrorText(error: FormError?): String {
    return when (error) {
        FormError.InvalidPhoneNumber -> stringResource(R.string.login_error_msg_invalid_number)
        is FormError.PasswordTooShort -> {
            val minLength = error.minLength
            stringResource(R.string.login_error_msg_too_short_password, minLength)
        }

        else -> stringResource(R.string.empty_string)
    }
}