package com.sergediame.auth

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sergediame.data.AuthenticationService
import org.koin.dsl.module

val authModule = module {
    single<AuthenticationService> { FirebaseAuthenticationService(auth = Firebase.auth) }
}



