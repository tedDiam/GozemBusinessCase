package com.sergediame.network

import com.sergediame.data.InfoDataSource
import com.sergediame.data.home.HomeScreenContentDataSource
import com.sergediame.network.home.HomeScreenContentDataSourceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single { provideKtorClient() }
    single { ApiService(get()) }
    single { ApiMock() }
    single<HomeScreenContentDataSource> { HomeScreenContentDataSourceImpl(get()) }
    single<InfoDataSource> { InfoDataSourceImpl(get()) }
}

fun provideKtorClient(): HttpClient {
    return HttpClient(OkHttp) {

        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }

        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }

        install(WebSockets) {
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
        }

    }
}