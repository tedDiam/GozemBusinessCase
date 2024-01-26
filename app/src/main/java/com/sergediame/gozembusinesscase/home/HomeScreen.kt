package com.sergediame.gozembusinesscase.home


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sergediame.domain.User
import com.sergediame.domain.entity.HomeScreenContentItem
import com.sergediame.gozembusinesscase.components.CustomTopAppBar
import com.sergediame.gozembusinesscase.common.UiState
import com.sergediame.gozembusinesscase.common.UiStateWrapper
import com.sergediame.gozembusinesscase.ui.model.UiHomeScreenContentItem
import com.sergediame.gozembusinesscase.ui.model.toUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeRoute(
    viewModel: HomeViewModel = koinViewModel(),
    navController: NavHostController = rememberNavController(),
    onMenuItemClicked: (route: String) -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onNavigateToUnAuthenticated: () -> Unit,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    HomeScreen(
        uiState = uiState,
        navController = navController,
        coroutineScope = coroutineScope,
        drawerState = drawerState,
        onMenuItemClicked = onMenuItemClicked,
        onSignedOut = {
            viewModel.onUiEvent(uiEvent = HomeUiEvent.LogOut).also {
                onNavigateToUnAuthenticated()
            }
        }
    )

}

@Composable
fun HomeScreen(
    uiState: UiState<List<HomeScreenContentItem>>,
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    onMenuItemClicked: (route: String) -> Unit,
    onSignedOut: () -> Unit,
) {
    UiStateWrapper(uiState = uiState) { content ->
        HomeContent(
            content = content,
            navController = navController,
            coroutineScope = coroutineScope,
            drawerState = drawerState,
            onMenuItemClicked = onMenuItemClicked,
            onSignedOut = onSignedOut
        )
    }

}


@Composable
fun HomeContent(
    content: List<HomeScreenContentItem>,
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    onMenuItemClicked: (route: String) -> Unit,
    onSignedOut: () -> Unit,
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val drawerWidth = screenWidth * 80 / 100

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet(
            modifier = Modifier.width(drawerWidth)
        ) {
            HomeDrawerContent(
                user = User("rere", "dsds"),
                onSignedOut = onSignedOut
            ) { route ->
                coroutineScope.launch {
                    drawerState.close()
                }

                navController.navigate(route)
            }
        }
    }) {

        RenderHome(
            drawerState = drawerState,
            items = content,
            onMenuItemClicked = onMenuItemClicked
        )

    }


}


@Composable
fun RenderHome(
    drawerState: DrawerState,
    items: List<HomeScreenContentItem>,
    onMenuItemClicked: (route: String) -> Unit
) {
    Column {
        CustomTopAppBar(drawerState)
        Scaffold { innerPadding ->
            Column {
                items.forEach { item ->
                    HorizontalPanel(
                        item = item.toUi(),
                        modifier = Modifier.padding(innerPadding),
                        onMenuItemClicked = onMenuItemClicked
                    )
                }
            }

        }
    }

}


@Composable
fun HorizontalPanel(
    item: UiHomeScreenContentItem, modifier: Modifier,
    onMenuItemClicked: (route: String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = item.content.color),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row(Modifier.clickable {
            onMenuItemClicked(item.content.route)
        }) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = item.content.icon,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = item.type.uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}





