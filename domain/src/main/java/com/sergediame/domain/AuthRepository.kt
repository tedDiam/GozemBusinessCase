package com.sergediame.domain

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<User?>

    suspend fun signInStream(email: String, password: String)
    suspend fun signOutStream()
    suspend fun signUpStream(email: String, password: String)
}