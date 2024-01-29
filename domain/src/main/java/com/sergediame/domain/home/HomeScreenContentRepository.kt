package com.sergediame.domain.home

import com.sergediame.domain.entity.HomeScreenContentItem
import kotlinx.coroutines.flow.Flow


interface HomeScreenContentRepository {
   val content:
           Flow<List<HomeScreenContentItem>>
}


