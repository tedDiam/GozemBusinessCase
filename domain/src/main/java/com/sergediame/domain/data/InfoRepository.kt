package com.sergediame.domain.data

import kotlinx.coroutines.flow.Flow

interface InfoRepository {
    fun getData(wsURL:String): Flow<String>
}