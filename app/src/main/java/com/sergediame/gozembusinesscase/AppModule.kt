package com.sergediame.gozembusinesscase

import com.sergediame.gozembusinesscase.auth.login.LoginViewModel
import com.sergediame.gozembusinesscase.auth.register.RegistrationViewModel
import com.sergediame.gozembusinesscase.data.InfoViewModel
import com.sergediame.gozembusinesscase.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainActivityViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegistrationViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { InfoViewModel(get()) }
}