package com.sergediame.gozembusinesscase.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun MenuButton(
    @StringRes title: Int,
    icon: Int,
    isEnabled: Boolean = true,
    btnClicked: () -> Unit
) {
    TextButton(
        onClick = {
            if (isEnabled) btnClicked()
        },
        enabled = isEnabled,
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = CircleShape
                    )
                    .padding(8.dp),
                tint = MaterialTheme.colorScheme.background
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                stringResource(id = title),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp),
                color = if (isEnabled) LocalContentColor.current.copy(alpha = ContentAlpha.medium) else LocalContentColor.current.copy(
                    alpha = ContentAlpha.disabled
                ),
                fontWeight = FontWeight.W500,
                fontSize = 14.sp
            )
        }
    }
    Divider(Modifier.padding(top = 8.dp))
}


@Composable
fun AddMenuItem(
    item: Item.Menu,
    navController: NavHostController
) {
    MenuButton(title = item.title, icon = item.icon, item.isEnabled) {
        navController.navigate(item.route)
    }
}

@Composable
fun AddTitleItem(
    item: Item.Title,
) {
    Text(
        stringResource(id = item.text),
       //textAlign = TextAlign.Center,
        modifier = Modifier.padding(start = 4.dp, top = 16.dp, bottom = 8.dp),
        fontWeight = FontWeight.W500,
        fontSize = 18.sp
    )

}


sealed class Item {
    data class Menu(
        @StringRes val title: Int,
        @DrawableRes val icon: Int,
        val route: String = "",
        val isEnabled: Boolean = true
    ) : Item()

    data class Title(@StringRes val text: Int) : Item()
}

