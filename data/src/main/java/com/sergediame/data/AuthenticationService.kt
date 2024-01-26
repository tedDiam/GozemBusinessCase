package com.sergediame.data


import com.sergediame.domain.Response
import com.sergediame.domain.User

interface AuthenticationService {
    val currentUser: User?

    suspend fun signIn(email: String, password: String)
    suspend fun signOut()
    suspend fun signUp(email: String, password: String)
}