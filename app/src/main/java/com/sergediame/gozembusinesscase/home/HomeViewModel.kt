package com.sergediame.gozembusinesscase.home


import androidx.lifecycle.viewModelScope
import com.sergediame.domain.AuthRepository
import com.sergediame.domain.HomeScreenContentRepository
import com.sergediame.domain.asResult
import com.sergediame.domain.entity.HomeScreenContentItem
import com.sergediame.gozembusinesscase.common.DataViewModel
import com.sergediame.gozembusinesscase.common.UiState
import com.sergediame.gozembusinesscase.common.asUiState
import kotlinx.coroutines.launch


class HomeViewModel(
    private val contentRepository: HomeScreenContentRepository,
    private val authRepository: AuthRepository
) : DataViewModel<UiState<List<HomeScreenContentItem>>, HomeUiEvent>(UiState.Loading) {
    init {
        setState {
            contentRepository.content .asResult().asUiState()
        }
    }


    override fun onUiEvent(uiEvent: HomeUiEvent) {
        when(uiEvent){
            HomeUiEvent.LogOut -> signOut()
        }
    }

    private fun signOut() {

        viewModelScope.launch {
            authRepository.signOutStream()
        }

    }





}
