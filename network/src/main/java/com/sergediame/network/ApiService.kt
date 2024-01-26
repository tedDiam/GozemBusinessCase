package com.sergediame.network

import com.sergediame.data.response.HomeScreenContentResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val httpClient: HttpClient) {

    suspend fun getHomeScreenContent(): HomeScreenContentResponse {
        return httpClient.get("file:///network/mocked_api_response.json").body()
    }

}