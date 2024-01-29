package com.sergediame.network.home

import com.sergediame.data.home.HomeScreenContentDataSource
import com.sergediame.data.response.toDomain
import com.sergediame.domain.entity.HomeScreenContentItem
import com.sergediame.network.ApiMock

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