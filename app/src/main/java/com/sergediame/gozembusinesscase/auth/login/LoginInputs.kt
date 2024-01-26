package com.sergediame.gozembusinesscase.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.sergediame.domain.form.LoginForm
import com.sergediame.gozembusinesscase.R
import com.sergediame.gozembusinesscase.components.customComposableViews.EmailTextField
import com.sergediame.gozembusinesscase.components.customComposableViews.NormalButton
import com.sergediame.gozembusinesscase.components.customComposableViews.PasswordTextField
import com.sergediame.gozembusinesscase.ui.theme.dimens
import com.sergediame.gozembusinesscase.utils.getErrorText

@Composable
fun LoginInputs(
    formState: LoginForm,
    onMobileChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit
) {

    // Login Inputs Section
    Column(modifier = Modifier.fillMaxWidth()) {
        // Email or Mobile Number
        EmailTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.dimens.large),
            value = formState.email.value,
            onValueChange = onMobileChange,
            label = stringResource(id = R.string.login_mobile_phone_label),
            isError = formState.email.hasError,
            errorText = getErrorText(error = formState.email.error)
        )

        // Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.dimens.large),
            value = formState.password.value,
            onValueChange = onPasswordChange,
            label = stringResource(id = R.string.login_password_label),
            isError = formState.password.hasError,
            errorText = getErrorText(error = formState.password.error),
            imeAction = ImeAction.Done
        )

        // Login Submit Button
        NormalButton(
            modifier = Modifier.padding(top = MaterialTheme.dimens.large),
            text = stringResource(id = R.string.login_button_text),
            onClick = onSubmit
        )

    }
}


