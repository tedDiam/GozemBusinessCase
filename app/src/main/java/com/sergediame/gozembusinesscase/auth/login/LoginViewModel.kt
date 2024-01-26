package com.sergediame.gozembusinesscase.auth.login

import com.sergediame.gozembusinesscase.common.FormViewModel
import com.sergediame.gozembusinesscase.common.UiState
import com.sergediame.gozembusinesscase.common.asUiState
import com.sergediame.domain.AuthRepository
import com.sergediame.domain.form.LoginForm
import com.sergediame.domain.resultOf

class LoginViewModel(
    private val authRepository: AuthRepository
) : FormViewModel<UiState<Unit>, LoginUiEvent, LoginForm>(
    initialState = UiState.Initial(data = Unit),
    initialFormState = LoginForm.INITIAL
) {
    override fun onUiEvent(uiEvent: LoginUiEvent) {
        when (uiEvent) {
            is LoginUiEvent.EmailOrMobileChanged -> updateFormState {
                it.copy(email = uiEvent.input)
            }

            is LoginUiEvent.PasswordChanged -> updateFormState {
                it.copy(password = uiEvent.input)
            }

            is LoginUiEvent.Submit -> handleFormSubmission(formState.value)
        }
    }

    private fun logInStream(phoneNumber: String, password: String) = resultOf {
        authRepository.signInStream(phoneNumber, password)
    }


    override fun handleFormSubmission(form: LoginForm) {

        with(form) {
            if (isValid) {
                setState {
                    logInStream(
                        email.value,
                        password.value
                    ).asUiState()
                }
            }
        }
    }

}


