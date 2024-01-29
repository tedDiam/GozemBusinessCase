package com.sergediame.domain.auth

import com.sergediame.domain.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<User?>

    suspend fun signInStream(email: String, password: String)
    suspend fun signOutStream()
    suspend fun signUpStream(email: String, password: String)
}