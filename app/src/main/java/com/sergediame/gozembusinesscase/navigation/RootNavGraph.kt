package com.sergediame.gozembusinesscase.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sergediame.gozembusinesscase.auth.authenticationGraph
import com.sergediame.gozembusinesscase.home.DATA_SECTION
import com.sergediame.gozembusinesscase.home.HomeRoute
import com.sergediame.gozembusinesscase.home.MAP_SECTION
import com.sergediame.gozembusinesscase.home.PROFILE_SECTION
import com.sergediame.gozembusinesscase.profile.ProfileRoute
import com.sergediame.gozembusinesscase.profile.UiProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun RootNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    NavHost(
        navController = navController,
        route = Graph.Root.route,
        startDestination = startDestination
    ) {
        authenticationGraph(navController = navController) {
            navController.navigate(route = Graph.Home.route)
        }
        composable(route = Graph.Home.route) {
            HomeRoute(
                onNavigateToUnAuthenticated = {
                    navController.navigate(route = Graph.Auth.route) {
                        popUpTo(Graph.Home.route) {
                            inclusive = true
                        }
                    }
                },
                onMenuItemClicked = { item ->
                    when (item.type) {
                        PROFILE_SECTION -> {
                            val encodedImageLink = URLEncoder.encode(item.content.image, StandardCharsets.UTF_8.toString())
                            navController.navigate(route = item.content.route + "/$encodedImageLink/${item.content.name}/${item.content.email}")
                        }
                        MAP_SECTION -> navController.navigate(route = item.content.route)
                        DATA_SECTION -> navController.navigate(route = item.content.route)
                    }
                })
        }
        composable(
            route = Graph.Profile.route+"/{image}/{name}/{email}",
            arguments = profileArgs
        ) {  backStackEntry ->
            val encodedImageLink = backStackEntry.arguments?.getString("image")
            val decodedImageLink = URLDecoder.decode(encodedImageLink, StandardCharsets.UTF_8.toString())

            ProfileRoute(

                profile = UiProfile(
                    image = decodedImageLink,
                    name = backStackEntry.arguments?.getString("name")?: "",
                    email = backStackEntry.arguments?.getString("email")?: ""
                )
            )
        }
        composable(route = Graph.Map.route) {
            Text("Map Screen")
        }
        composable(route = Graph.Data.route) {
            Text("Data Screen")
        }

    }
}

val profileArgs = listOf(
    navArgument("image") {
    type = NavType.StringType
}, navArgument("name") {
    type = NavType.StringType
}, navArgument("email") {
    type = NavType.StringType
})

val dataArgs = listOf(
    navArgument("source") {
        type = NavType.StringType
    }, navArgument("value") {
        type = NavType.StringType
    })

val mapArgs = listOf(
    navArgument("pin") {
        type = NavType.StringType
    }, navArgument("lat") {
        type = NavType.StringType
    }, navArgument("lng") {
        type = NavType.StringType
    })

sealed class Graph(override val route: String, override val arguments: List<NamedNavArgument> ) : Destination {
    data object Root: Graph( route ="root", emptyList())
    data object Map: Graph( route ="map", mapArgs)
    data object Data: Graph( route ="data", dataArgs)
    data object Profile: Graph( route ="profile", arguments = profileArgs)
    data object Home: Graph( route ="home", emptyList())
    data object Auth: Graph( route ="auth", emptyList())

}
