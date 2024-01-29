package com.sergediame.data.auth


import com.sergediame.domain.User

interface AuthenticationService {
    val currentUser: User?

    suspend fun signIn(email: String, password: String)
    suspend fun signOut()
    suspend fun signUp(email: String, password: String)
}