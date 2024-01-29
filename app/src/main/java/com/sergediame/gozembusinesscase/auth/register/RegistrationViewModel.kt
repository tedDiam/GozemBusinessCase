package com.sergediame.gozembusinesscase.auth.register

import com.sergediame.gozembusinesscase.common.FormViewModel
import com.sergediame.gozembusinesscase.common.UiState
import com.sergediame.domain.auth.AuthRepository
import com.sergediame.domain.form.RegistrationForm
import com.sergediame.domain.resultOf
import com.sergediame.gozembusinesscase.common.asUiState

class RegistrationViewModel(
        private val authRepository: AuthRepository
) : FormViewModel<UiState<Unit>, RegistrationUiEvent, RegistrationForm>(
        initialState = UiState.Initial(data = Unit),
        initialFormState = RegistrationForm.INITIAL
) {

    override fun onUiEvent(uiEvent: RegistrationUiEvent) {
        when (uiEvent) {
            is RegistrationUiEvent.ConfirmPasswordChanged -> updateFormState {
                it.copy(confirmPassword = uiEvent.input)
            }

            is RegistrationUiEvent.EmailChanged -> updateFormState {
                it.copy(emailAddress = uiEvent.input)
            }

            is RegistrationUiEvent.PasswordChanged -> updateFormState {
                it.copy(password = uiEvent.input)
            }

            RegistrationUiEvent.Submit -> handleFormSubmission(formState.value)
        }
    }

    private fun signUpStream(email: String, password: String) = resultOf {
        authRepository.signUpStream(email, password)
    }

    override fun handleFormSubmission(form: RegistrationForm) {
        with(form) {
            if (isValid) {
                setState {
                    signUpStream(emailAddress.value, password.value).asUiState()
                }
            }
        }
    }

}