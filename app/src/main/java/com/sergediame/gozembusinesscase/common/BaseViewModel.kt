package com.sergediame.gozembusinesscase.common

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.seconds

abstract class BaseViewModel : ViewModel() {

    init {
        Log.d(this::class.simpleName, "created")
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
        Log.d(this::class.simpleName, "destroyed")
    }

    protected inline fun <T> MutableStateFlow<T>.update(function: (T) -> T) {
        while (true) {
            val prevValue = value
            val nextValue = function(prevValue)
            if (compareAndSet(prevValue, nextValue)) {
                return
            }
        }
    }

    protected fun <T> Flow<T>.asStateFlow(defaultState: T): StateFlow<T> =
        stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(1.seconds.inWholeMilliseconds), defaultState
        )

    protected fun <T> Flow<T>.asMutableState(state: T): MutableStateFlow<T> =
        MutableStateFlow(state)

    protected fun <T> Flow<T>.shared(): SharedFlow<T> =
        shareIn(viewModelScope, SharingStarted.WhileSubscribed(1.seconds.inWholeMilliseconds), 1)

}