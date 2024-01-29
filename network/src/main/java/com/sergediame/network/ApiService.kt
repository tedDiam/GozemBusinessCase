package com.sergediame.network

import com.sergediame.data.response.HomeScreenContentResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.get

class ApiService(private val httpClient: HttpClient) {

    suspend fun getHomeScreenContent(): HomeScreenContentResponse {
        return httpClient.get("file:///network/mocked_api_response.json").body()
    }


    suspend fun getData(wsUrl: String): String {
        var data: String? = null
        httpClient.webSocket(wsUrl) {
            data = receiveDeserialized<String>()
        }
        return data.orEmpty()
    }


}