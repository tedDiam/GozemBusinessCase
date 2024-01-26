package com.sergediame.data

import com.sergediame.domain.AuthRepository
import com.sergediame.domain.HomeScreenContentRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {

    single<CoroutineDispatcher> { Dispatchers.IO }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<HomeScreenContentRepository> { HomeScreenContentRepositoryImpl(get(), get()) }


}
