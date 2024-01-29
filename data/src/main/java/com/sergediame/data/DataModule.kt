package com.sergediame.data

import com.sergediame.data.auth.AuthRepositoryImpl
import com.sergediame.data.home.HomeScreenContentRepositoryImpl
import com.sergediame.domain.auth.AuthRepository
import com.sergediame.domain.data.InfoRepository
import com.sergediame.domain.home.HomeScreenContentRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {

    single<CoroutineDispatcher> { Dispatchers.IO }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<HomeScreenContentRepository> { HomeScreenContentRepositoryImpl(get(), get()) }
    single<InfoRepository> { InfoRepositoryImpl(get(), get()) }


}
