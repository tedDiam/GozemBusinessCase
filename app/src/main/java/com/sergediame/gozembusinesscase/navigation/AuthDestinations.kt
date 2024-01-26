package com.sergediame.gozembusinesscase.navigation


sealed class AuthDestination(override val route: String) : Destination {
    data object Login: AuthDestination( route ="login")
    data object Register: AuthDestination( route ="register")
}
