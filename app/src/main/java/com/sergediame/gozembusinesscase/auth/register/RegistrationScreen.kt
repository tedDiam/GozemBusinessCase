package com.sergediame.gozembusinesscase.auth.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sergediame.gozembusinesscase.common.FormUiStateWrapper
import com.sergediame.gozembusinesscase.components.customComposableViews.SmallClickableWithIconAndText
import com.sergediame.gozembusinesscase.components.customComposableViews.TitleText
import com.sergediame.domain.form.EmailAddressField
import com.sergediame.domain.form.PasswordField
import com.sergediame.domain.form.RegistrationForm
import com.sergediame.gozembusinesscase.R
import com.sergediame.gozembusinesscase.common.UiState
import com.sergediame.gozembusinesscase.ui.theme.GozemBusinessCaseTheme
import com.sergediame.gozembusinesscase.ui.theme.dimens
import org.koin.androidx.compose.koinViewModel


@Composable
fun RegistrationRoute(
    viewModel: RegistrationViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val formState by viewModel.formState.collectAsStateWithLifecycle()

    RegistrationScreen(
        uiState = uiState,
        formState = formState,
        onEmailIdChange = { inputString ->
            viewModel.onUiEvent(
                uiEvent = RegistrationUiEvent.EmailChanged(
                    input = EmailAddressField(inputString)
                )
            )
        },
        onPasswordChange = { inputString ->
            viewModel.onUiEvent(
                uiEvent = RegistrationUiEvent.PasswordChanged(
                    input = PasswordField(inputString)
                )
            )
        },
        onConfirmPasswordChange = { inputString ->
            viewModel.onUiEvent(
                uiEvent = RegistrationUiEvent.ConfirmPasswordChanged(
                    input = PasswordField(inputString)
                )
            )
        },
        onSubmit = {
            viewModel.onUiEvent(uiEvent = RegistrationUiEvent.Submit)
        },
        onNavigateBack = onNavigateBack,
        onNavigateToAuthenticatedRoute = onNavigateToAuthenticatedRoute
    )


}


@Composable
fun RegistrationScreen(
    uiState: UiState<Unit>,
    formState: RegistrationForm,
    onEmailIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {
    FormUiStateWrapper(
        uiState = uiState,
        onInitial = {
            RegistrationContent(formState = formState,
                onEmailIdChange = onEmailIdChange,
                onPasswordChange = onPasswordChange,
                onConfirmPasswordChange = onConfirmPasswordChange,
                onSubmit = onSubmit,
                onNavigateBack =onNavigateBack,
                onNavigateToAuthenticatedRoute = onNavigateToAuthenticatedRoute)

        },
        onSuccess = {
            onNavigateToAuthenticatedRoute()
        }
    )

}

@Composable
fun RegistrationContent(
    formState: RegistrationForm,
    onEmailIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {
    // Full Screen Content
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {

        // Back Button Icon
        SmallClickableWithIconAndText(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.large)
                .padding(top = MaterialTheme.dimens.large),
            iconContentDescription = "",
            iconVector = Icons.Outlined.ArrowBack,
            text = stringResource(id = R.string.back_to_login),
            onClick = onNavigateBack
        )

        // Main card Content for Registration
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimens.large)
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.dimens.large)
                    .padding(bottom = MaterialTheme.dimens.extraLarge)
            ) {

                // Heading Registration
                TitleText(
                    modifier = Modifier.padding(top = MaterialTheme.dimens.large),
                    text = stringResource(id = R.string.registration_heading_text),
                    style = MaterialTheme.typography.headlineLarge,
                )

                /**
                 * Registration Inputs Composable
                 */
                RegistrationInputs(
                    registrationState = formState,
                    onEmailIdChange = onEmailIdChange,
                    onPasswordChange = onPasswordChange,
                    onConfirmPasswordChange = onConfirmPasswordChange,
                    onSubmit = onSubmit
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegistrationScreen() {
    GozemBusinessCaseTheme {
        RegistrationContent(
            formState = RegistrationForm.INITIAL,
            onEmailIdChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onSubmit = {},
            onNavigateBack = {},
            onNavigateToAuthenticatedRoute = {}

        )
    }
}