package com.sergediame.network

import com.sergediame.data.InfoDataSource

class InfoDataSourceImpl(private val apiService: ApiService): InfoDataSource {
    override suspend fun getData(wsUrl: String): String {
        return apiService.getData(wsUrl)
    }
}