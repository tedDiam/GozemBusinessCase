package com.sergediame.network

import com.sergediame.data.HomeScreenContentDataSource
import com.sergediame.data.response.toDomain
import com.sergediame.domain.entity.HomeScreenContentItem

class HomeScreenContentDataSourceImpl(
    private val apiService: ApiMock
) :
    HomeScreenContentDataSource {
    override suspend fun getHomeScreenContent(): List<HomeScreenContentItem> {

        //return apiService.getHomeScreenContent().toDomain() as HomeScreenContent

        return apiService.getHomeScreenContent().map {
            it.toDomain()
        }

    }
}