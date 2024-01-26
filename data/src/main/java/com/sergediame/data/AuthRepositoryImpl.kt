package com.sergediame.data

import com.sergediame.domain.AuthRepository
import com.sergediame.domain.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val authService: AuthenticationService,
    private val iODispatcher: CoroutineDispatcher
) : AuthRepository {

    override val currentUser: Flow<User?>
        get() = flowOf(authService.currentUser)

    override suspend fun signInStream(email: String, password: String) {
        withContext(iODispatcher) {
            authService.signIn(email, password)
        }
    }

    override suspend fun signOutStream() {
        withContext(iODispatcher) {
            authService.signOut()
        }
    }

    override suspend fun signUpStream(email: String, password: String) {
        withContext(iODispatcher) {
            authService.signUp(email, password)
        }
    }

}