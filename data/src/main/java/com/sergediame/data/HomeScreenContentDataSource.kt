package com.sergediame.data

import com.sergediame.domain.entity.HomeScreenContentItem

interface HomeScreenContentDataSource {
   suspend fun getHomeScreenContent():List<HomeScreenContentItem>
}