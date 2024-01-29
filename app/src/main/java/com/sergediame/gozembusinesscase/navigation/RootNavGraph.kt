package com.sergediame.gozembusinesscase.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sergediame.domain.orZero
import com.sergediame.gozembusinesscase.auth.authenticationGraph
import com.sergediame.gozembusinesscase.data.DataRoute
import com.sergediame.gozembusinesscase.data.UiData
import com.sergediame.gozembusinesscase.home.DATA_SECTION
import com.sergediame.gozembusinesscase.home.HomeRoute
import com.sergediame.gozembusinesscase.home.MAP_SECTION
import com.sergediame.gozembusinesscase.home.PROFILE_SECTION
import com.sergediame.gozembusinesscase.map.MapRoute
import com.sergediame.gozembusinesscase.map.UiMap
import com.sergediame.gozembusinesscase.profile.ProfileRoute
import com.sergediame.gozembusinesscase.profile.UiProfile
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
                            val encodedImageLink = URLEncoder.encode(
                                item.content.image,
                                StandardCharsets.UTF_8.toString()
                            )
                            navController.navigate(route = item.content.route + "/$encodedImageLink/${item.content.name}/${item.content.email}")
                        }

                        MAP_SECTION -> {
                            val encodedPinLink = URLEncoder.encode(
                                item.content.pin,
                                StandardCharsets.UTF_8.toString()
                            )
                            navController.navigate(route = item.content.route + "/$encodedPinLink/${item.content.lat}/${item.content.lng}")
                        }

                        DATA_SECTION -> {
                            val encodedWsUrl = URLEncoder.encode(
                                item.content.source,
                                StandardCharsets.UTF_8.toString()
                            )
                            navController.navigate(route = item.content.route + "/${encodedWsUrl}/${item.content.value}")
                        }
                    }
                })
        }
        composable(
            route = Graph.Profile.route + "/{image}/{name}/{email}",
            arguments = profileArgs
        ) { backStackEntry ->
            val encodedImageLink = backStackEntry.arguments?.getString("image")
            val decodedImageLink =
                URLDecoder.decode(encodedImageLink, StandardCharsets.UTF_8.toString())

            ProfileRoute(
                profile = UiProfile(
                    image = decodedImageLink,
                    name = backStackEntry.arguments?.getString("name") ?: "",
                    email = backStackEntry.arguments?.getString("email") ?: ""
                )
            )
        }
        composable(
            route = Graph.Map.route + "/{pin}/{lat}/{lng}",
            arguments = mapArgs
        ) { backStackEntry ->
            val encodedPinLink = backStackEntry.arguments?.getString("pin")
            val decodedPinLink =
                URLDecoder.decode(encodedPinLink, StandardCharsets.UTF_8.toString())

            MapRoute(
                map = UiMap(
                    pin = decodedPinLink,
                    lat = backStackEntry.arguments?.getDouble("lat").orZero(),
                    lng = backStackEntry.arguments?.getDouble("lng").orZero(),
                )
            )
        }

        composable(
            route = Graph.Data.route + "/{source}/{value}",
            arguments = dataArgs
        ) { backStackEntry ->
            val encodedWsUrl = backStackEntry.arguments?.getString("source")
            val decodedWsUrl =
                URLDecoder.decode(encodedWsUrl, StandardCharsets.UTF_8.toString())
            DataRoute(
                uiData = UiData(
                    decodedWsUrl,
                    backStackEntry.arguments?.getString("value") ?: ""
                )
            )
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

sealed class Graph(
    override val route: String,
    override val arguments: List<NamedNavArgument>
) : Destination {
    data object Root : Graph(route = "root", emptyList())
    data object Map : Graph(route = "map", mapArgs)
    data object Data : Graph(route = "data", dataArgs)
    data object Profile : Graph(route = "profile", arguments = profileArgs)
    data object Home : Graph(route = "home", emptyList())
    data object Auth : Graph(route = "auth", emptyList())

}
