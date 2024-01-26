package com.sergediame.gozembusinesscase.common

import androidx.lifecycle.viewModelScope
import com.sergediame.domain.form.Form
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class FormViewModel<UI_STATE : UiState<*>, UI_EVENT : UiEvent, FORM_STATE : Form>(
    initialState: UI_STATE, initialFormState: FORM_STATE
) : BaseViewModel() {

    private val _formState: MutableStateFlow<FORM_STATE> = MutableStateFlow(initialFormState)

    val formState: StateFlow<FORM_STATE> = _formState.asStateFlow()

    private val _uiState: MutableStateFlow<UI_STATE> = MutableStateFlow(initialState)
    val uiState: StateFlow<UI_STATE> get() = _uiState.asStateFlow()

    abstract fun onUiEvent(uiEvent: UI_EVENT)


    protected fun updateFormState(formState: (FORM_STATE) -> FORM_STATE) {
        _formState.update(formState)
    }

    abstract fun handleFormSubmission(form: FORM_STATE)


    protected fun setState(newStateBlock: () -> Flow<UI_STATE>) = viewModelScope.launch {
        newStateBlock().collect { newState ->
            _uiState.tryEmit(newState)
        }
    }
}