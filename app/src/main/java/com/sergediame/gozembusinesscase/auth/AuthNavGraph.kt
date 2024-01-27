package com.sergediame.gozembusinesscase.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sergediame.gozembusinesscase.navigation.AuthDestination
import com.sergediame.gozembusinesscase.auth.login.LoginRoute
import com.sergediame.gozembusinesscase.navigation.Graph
import com.sergediame.gozembusinesscase.auth.register.RegistrationRoute


fun NavGraphBuilder.authenticationGraph(
        navController: NavController,
        onNavigateToAuthenticated: () -> Unit
) {

    navigation(
            route = Graph.Auth.route,
            startDestination = AuthDestination.Login.route
    ) {
        composable(route = AuthDestination.Login.route) {
            LoginRoute(
                    onNavigateToRegistration = {
                        navController.navigate(route = AuthDestination.Register.route)
                    },
                    onNavigateToAuthenticated = {
                        onNavigateToAuthenticated()
                    })
        }
        composable(route = AuthDestination.Register.route) {
            RegistrationRoute(
                    onNavigateBack = {
                        navController.navigateUp()
                    },
                    onNavigateToAuthenticatedRoute = {
                        navController.navigate(route = AuthDestination.Login.route)
                    }
            )

        }

    }
}