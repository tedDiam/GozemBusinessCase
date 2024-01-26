package com.sergediame.network

import com.sergediame.data.response.HomeScreenContentItemResponse
import com.sergediame.data.response.HomeScreenContentResponse
import com.sergediame.data.response.MOCKED_HOME_SCREEN_CONTENT_RESPONSE
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiMock {

    private val engine = MockEngine { _ ->
        respond(
            content = MOCKED_HOME_SCREEN_CONTENT_RESPONSE,
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    private val httpClient = HttpClient(engine= engine) {

        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
                prettyPrint = true
               // useArrayPolymorphism = true
            })
        }

        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }


    }


    suspend fun getHomeScreenContent(): ArrayList<HomeScreenContentItemResponse> {
        return httpClient.get("file:///network/mocked_api_response.json").body()
    }



}