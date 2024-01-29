package com.sergediame.gozembusinesscase.data

import com.sergediame.gozembusinesscase.common.UiEvent

sealed class InfoUiEvent : UiEvent {
    data class Fetch(val wsUrl:String) : InfoUiEvent()

}