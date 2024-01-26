package com.sergediame.gozembusinesscase.home

import com.sergediame.gozembusinesscase.common.UiEvent

sealed class HomeUiEvent : UiEvent {
    data object LogOut : HomeUiEvent()

}