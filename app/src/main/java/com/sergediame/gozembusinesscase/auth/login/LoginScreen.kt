package com.sergediame.gozembusinesscase.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.sergediame.gozembusinesscase.common.FormUiStateWrapper
import com.sergediame.gozembusinesscase.components.customComposableViews.TitleText
import com.sergediame.domain.form.EmailAddressField
import com.sergediame.domain.form.LoginForm
import com.sergediame.domain.form.PasswordField
import com.sergediame.gozembusinesscase.R
import com.sergediame.gozembusinesscase.common.UiState
import com.sergediame.gozembusinesscase.ui.theme.dimens
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginRoute(
    viewModel: LoginViewModel = koinViewModel(),
    onNavigateToRegistration: () -> Unit,
    onNavigateToAuthenticated: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val formState by viewModel.formState.collectAsStateWithLifecycle()

    LoginScreen(
        uiState = uiState,
        formState = formState,
        onMobileChange = { inputString ->
            viewModel.onUiEvent(
                uiEvent = LoginUiEvent.EmailOrMobileChanged(
                    EmailAddressField(inputString)
                )
            )
        },
        onPasswordChange = { inputString ->
            viewModel.onUiEvent(
                uiEvent = LoginUiEvent.PasswordChanged(
                    PasswordField(inputString)
                )
            )
        },
        onSubmit = {
            viewModel.onUiEvent(
                uiEvent = LoginUiEvent.Submit
            )
        },
        onNavigateToRegistration = onNavigateToRegistration,
        onNavigateToAuthenticated = onNavigateToAuthenticated
    )

}

@Composable
fun LoginScreen(
    uiState: UiState<Unit>,
    formState: LoginForm,
    onMobileChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onNavigateToRegistration: () -> Unit,
    onNavigateToAuthenticated: () -> Unit,
) {
    FormUiStateWrapper(uiState = uiState, onInitial = {
        LoginContent(
            formState = formState,
            onMobileChange = onMobileChange,
            onPasswordChange = onPasswordChange,
            onSubmit = onSubmit,
            onNavigateToRegistration = onNavigateToRegistration,
        )
    }, onSuccess = {
        onNavigateToAuthenticated()
    })
}

@Composable
fun LoginContent(
    formState: LoginForm,
    onMobileChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onNavigateToRegistration: () -> Unit,
) {

    // Full Screen Content
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Main card Content for Login
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

                // Heading Jetpack Compose
                TitleText(
                    modifier = Modifier
                        .padding(top = MaterialTheme.dimens.large)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )

                // Login Logo
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp)
                        .padding(top = MaterialTheme.dimens.small),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = R.drawable.ic_launcher_foreground)
                        .crossfade(enable = true)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = stringResource(id = R.string.login_heading_text)
                )

                // Heading Login
                TitleText(
                    modifier = Modifier.padding(top = MaterialTheme.dimens.large),
                    text = stringResource(id = R.string.login_heading_text),
                    style = MaterialTheme.typography.headlineLarge,

                    )

                // Login Inputs Composable
                LoginInputs(
                    formState = formState,
                    onMobileChange = onMobileChange,
                    onPasswordChange = onPasswordChange,
                    onSubmit = onSubmit
                )

            }
        }

        // Register Section
        Row(
            modifier = Modifier.padding(MaterialTheme.dimens.normal),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Don't have an account?
            Text(text = stringResource(id = R.string.do_not_have_account))

            //Register
            Text(
                modifier = Modifier
                    .padding(start = MaterialTheme.dimens.extraSmall)
                    .clickable {
                        onNavigateToRegistration.invoke()
                    },
                text = stringResource(id = R.string.register),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

}
