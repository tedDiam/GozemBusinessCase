package com.sergediame.data

import com.sergediame.domain.HomeScreenContentRepository
import com.sergediame.domain.entity.HomeScreenContentItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeScreenContentRepositoryImpl(
    private val dataSource: HomeScreenContentDataSource,
    private val iODispatcher: CoroutineDispatcher,
) : HomeScreenContentRepository {

    override val content: Flow<List<HomeScreenContentItem>>
        get() = flow {
            emit(dataSource.getHomeScreenContent())
        }.flowOn(iODispatcher)
}