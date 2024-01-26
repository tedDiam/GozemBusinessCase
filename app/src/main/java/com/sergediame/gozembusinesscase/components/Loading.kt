package com.sergediame.gozembusinesscase.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Loading() = CircularProgressIndicator(
    Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
)