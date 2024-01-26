package com.sergediame.gozembusinesscase.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sergediame.gozembusinesscase.auth.authenticationGraph
import com.sergediame.gozembusinesscase.home.HomeRoute


@Composable
fun RootNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = startDestination
    ) {
        authenticationGraph(navController = navController) {
            navController.navigate(route = Graph.HOME)
        }
        composable(route = Graph.HOME) {
            HomeRoute(
                onNavigateToUnAuthenticated = {
                    navController.navigate(route = Graph.AUTHENTICATION) {
                        popUpTo(Graph.HOME) {
                            inclusive = true
                        }
                    }
                },
                onMenuItemClicked = {route->
                    navController.navigate(route = route)
                })
        }
        composable(route = Graph.PROFILE) {
            Text("Profile Screen")
        }
        composable(route = Graph.MAP) {
            Text("Map Screen")
        }
        composable(route = Graph.DATA) {
            Text("Data Screen")
        }

    }
}

object Graph {
    const val ROOT = "root_graph"
    const val MAP = "seller_graph"
    const val DATA = "data_graph"
    const val PROFILE = "profile_graph"
    const val HOME = "home_graph"
    const val AUTHENTICATION = "authentication_graph"
}