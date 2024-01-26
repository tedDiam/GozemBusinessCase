package com.sergediame.domain

import com.sergediame.domain.entity.HomeScreenContentItem
import kotlinx.coroutines.flow.Flow


interface HomeScreenContentRepository {
   val content:
           Flow<List<HomeScreenContentItem>>
}


