package com.sergediame.gozembusinesscase.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

sealed interface Destination {
    val route: String
    val arguments: List<NamedNavArgument> get() = emptyList()
    val deepLinks: List<NavDeepLink> get() = emptyList()
}