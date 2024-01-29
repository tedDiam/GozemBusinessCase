package com.sergediame.data

interface InfoDataSource {

   suspend fun getData(wsUrl: String): String
}