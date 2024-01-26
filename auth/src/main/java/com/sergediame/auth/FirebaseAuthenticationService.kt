package com.sergediame.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sergediame.data.AuthenticationService
import com.sergediame.domain.User
import kotlinx.coroutines.tasks.await

class FirebaseAuthenticationService(
        private val auth: FirebaseAuth
) : AuthenticationService {

    override val currentUser get() = auth.currentUser?.toUser()


    override suspend fun signIn(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override suspend fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

}

fun FirebaseUser.toUser() = User(uuid = this.uid, this.email.orEmpty())



