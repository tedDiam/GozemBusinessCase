package com.sergediame.gozembusinesscase.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sergediame.domain.User
import com.sergediame.gozembusinesscase.R


@Composable
fun HomeDrawerContent(
    user: String,
    onSignedOut: () -> Unit,
    onMenuClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Hello ! $user", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))

        TextButton(modifier = Modifier.padding(8.dp), onClick = { onSignedOut() }) {
            Icon(
                imageVector = Icons.Filled.ExitToApp,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = stringResource(id = R.string.profile_sign_out),
                modifier = Modifier.padding(start = 8.dp),
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.W400
            )
        }
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
    }
}

