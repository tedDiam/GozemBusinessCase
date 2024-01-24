package com.sergediame.gozembusinesscase

interface AuthRepository {
    suspend fun signInStream(email: String, password: String)
    suspend fun signOutStream()
    suspend fun signUpStream(email: String, password: String)
}