package com.sergediame.gozembusinesscase


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.sergediame.domain.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivityViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _isLoggedState = MutableStateFlow(false)
    val isLoggedState: StateFlow<Boolean> = _isLoggedState

    init {
        viewModelScope.launch {
            authRepository.currentUser.collect { currentUser ->
                _isLoggedState.update {
                    currentUser!=null
                }
            }
        }
    }

}
