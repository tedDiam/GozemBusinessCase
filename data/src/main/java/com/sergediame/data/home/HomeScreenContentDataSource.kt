package com.sergediame.data.home

import com.sergediame.domain.entity.HomeScreenContentItem

interface HomeScreenContentDataSource {
   suspend fun getHomeScreenContent():List<HomeScreenContentItem>
}