package com.sergediame.gozembusinesscase.data

import com.sergediame.domain.asResult
import com.sergediame.domain.data.InfoRepository
import com.sergediame.gozembusinesscase.common.DataViewModel
import com.sergediame.gozembusinesscase.common.UiState
import com.sergediame.gozembusinesscase.common.asUiState

class InfoViewModel(
    private val infoRepository: InfoRepository
) : DataViewModel<UiState<String>, InfoUiEvent>(UiState.Loading) {

    override fun onUiEvent(uiEvent: InfoUiEvent) {
        when (uiEvent) {
            is InfoUiEvent.Fetch -> getData(uiEvent.wsUrl)
        }
    }

    private fun getData(wsUrl:String){
        setState {
            infoRepository.getData(wsUrl).asResult().asUiState()
        }
    }


}