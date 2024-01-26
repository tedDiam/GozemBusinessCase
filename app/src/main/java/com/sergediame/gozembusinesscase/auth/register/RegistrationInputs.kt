package com.sergediame.gozembusinesscase.auth.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.sergediame.gozembusinesscase.components.customComposableViews.EmailTextField
import com.sergediame.gozembusinesscase.components.customComposableViews.NormalButton
import com.sergediame.gozembusinesscase.components.customComposableViews.PasswordTextField
import com.sergediame.gozembusinesscase.utils.getErrorText
import com.sergediame.domain.form.RegistrationForm
import com.sergediame.gozembusinesscase.R
import com.sergediame.gozembusinesscase.ui.theme.dimens

@Composable
fun RegistrationInputs(
    registrationState: RegistrationForm,
    onEmailIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    // Login Inputs Section
    Column(modifier = Modifier.fillMaxWidth()) {

        // Email ID
        EmailTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.dimens.large),
            value = registrationState.emailAddress.value,
            onValueChange = onEmailIdChange,
            label = stringResource(id = R.string.registration_email_label),
            isError = registrationState.emailAddress.hasError,
            errorText = getErrorText(error = registrationState.emailAddress.error),
            imeAction = ImeAction.Next
        )


        // Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.dimens.large),
            value = registrationState.password.value,
            onValueChange = onPasswordChange,
            label = stringResource(id = R.string.registration_password_label),
            isError = registrationState.password.hasError,
            errorText = getErrorText(registrationState.password.error),
            imeAction = ImeAction.Next
        )

        // Confirm Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.dimens.large),
            value = registrationState.confirmPassword.value,
            onValueChange = onConfirmPasswordChange,
            label = stringResource(id = R.string.registration_confirm_password_label),
            isError = registrationState.confirmPassword.hasError,
            errorText = getErrorText(error = registrationState.confirmPassword.error),
            imeAction = ImeAction.Done
        )

        // Registration Submit Button
        NormalButton(
            modifier = Modifier.padding(top = MaterialTheme.dimens.extraLarge),
            text = stringResource(id = R.string.registration_button_text),
            onClick = onSubmit
        )


    }
}