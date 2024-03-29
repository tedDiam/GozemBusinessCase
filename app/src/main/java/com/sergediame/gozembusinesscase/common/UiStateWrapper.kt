package com.sergediame.gozembusinesscase.common

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun <T> UiStateWrapper(
    uiState: UiState<T>,
    onError: @Composable (String) -> Unit = { message ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = message,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    },
    onLoading: @Composable () -> Unit = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    },
    onEmpty: @Composable (String) -> Unit = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = it,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    },
    onInitial: @Composable () -> Unit = {

    },
    onSuccess: @Composable (T) -> Unit
) {
    when (uiState) {
        UiState.Loading -> onLoading()
        is UiState.Success -> onSuccess(uiState.data)
        is UiState.Error -> onError(uiState.message ?: "Unknown error")
        is UiState.Empty -> onEmpty(uiState.message ?: "No data")
        is UiState.Initial -> onInitial()
    }
}


@Composable
fun <T> FormUiStateWrapper(
    uiState: UiState<T>,
    onError: @Composable (String) -> Unit = { message ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = message,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    },
    onLoading: @Composable () -> Unit = {
        Log.d("Loading", "Here")
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    },
    onEmpty: @Composable (String) -> Unit = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = it,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    },
    onSuccess: (T) -> Unit,
    onInitial: @Composable () -> Unit = {

    }
) {
    when (uiState) {
        UiState.Loading -> onLoading()
        is UiState.Success -> onSuccess(uiState.data)
        is UiState.Error -> onError(uiState.message ?: "Unknown error")
        is UiState.Empty -> onEmpty(uiState.message ?: "No data")
        is UiState.Initial -> onInitial()
    }
}