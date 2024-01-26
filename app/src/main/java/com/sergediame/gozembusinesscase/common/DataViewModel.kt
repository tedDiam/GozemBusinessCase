package com.sergediame.gozembusinesscase.common

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class DataViewModel<UI_STATE : UiState<*>, UI_EVENT : UiEvent>(initialState: UI_STATE) :
    BaseViewModel() {

    private val _uiState: MutableStateFlow<UI_STATE> = MutableStateFlow(initialState)
    val uiState: StateFlow<UI_STATE> get() = _uiState.asStateFlow()

    abstract fun onUiEvent(uiEvent: UI_EVENT)


    protected fun setState(newStateBlock: () -> Flow<UI_STATE>) = viewModelScope.launch {
        newStateBlock().collect { newState ->
            _uiState.tryEmit(newState)
        }
    }

}