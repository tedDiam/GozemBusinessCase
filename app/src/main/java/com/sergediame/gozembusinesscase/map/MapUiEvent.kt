package com.sergediame.gozembusinesscase.map

import com.sergediame.gozembusinesscase.common.UiEvent

sealed class MapUiEvent : UiEvent {
    data object Granted : MapUiEvent()
    data object Revoked : MapUiEvent()

}