package com.sergediame.gozembusinesscase.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sergediame.gozembusinesscase.R
import kotlinx.coroutines.launch

@Composable
fun CustomTopAppBar(
    drawerState: DrawerState? = null,
    title: String = stringResource(id = R.string.app_name)
) {
    val coroutineScope = rememberCoroutineScope()

    TopAppBar(
        navigationIcon = {
            if (drawerState != null) {
                IconButton(onClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }) {
                    Icon(Icons.Filled.Menu, contentDescription = "", tint = MaterialTheme.colorScheme.onSecondary)
                }
            }
        },
        modifier = Modifier.padding( top = 18.dp),
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        backgroundColor = MaterialTheme.colorScheme.secondary
    )
}