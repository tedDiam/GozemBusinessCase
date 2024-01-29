package com.sergediame.gozembusinesscase.data


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sergediame.gozembusinesscase.common.UiState
import com.sergediame.gozembusinesscase.common.UiStateWrapper
import kotlinx.coroutines.job
import org.koin.androidx.compose.koinViewModel


@Composable
fun DataRoute(
    viewModel: InfoViewModel = koinViewModel(),
    uiData: UiData
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
       viewModel.onUiEvent(InfoUiEvent.Fetch(uiData.source))
    }

    DataScreen(uiState = uiState, uiData = uiData)
}

@Composable
fun DataScreen(uiState: UiState<String>, uiData: UiData) {



    UiStateWrapper(uiState = uiState) {
        DataContent(uiState = uiState, content = uiData)
    }
}

@Composable
fun DataContent(uiState: UiState<String>, content: UiData) {
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Information", style = MaterialTheme.typography.titleSmall)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            elevation = 4.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = content.value)

                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }

}









