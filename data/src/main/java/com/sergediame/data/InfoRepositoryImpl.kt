package com.sergediame.data

import com.sergediame.domain.data.InfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class InfoRepositoryImpl(
    private val dataSource: InfoDataSource,
    private val iODispatcher: CoroutineDispatcher
) : InfoRepository {
    override fun getData(wsURL: String): Flow<String> {
        return flow {
            emit(dataSource.getData(wsURL))
        }.flowOn(iODispatcher)
    }
}