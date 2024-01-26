package com.sergediame.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.coroutines.CoroutineContext

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable? = null) : Result<Nothing>
    data object Loading : Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> {
            Result.Success(it)
        }
        .onStart { emit(Result.Loading) }
        .catch { throwable ->
            emit(Result.Error(throwable))
            println("**** ${throwable.message} **** ")
            println("**** ${throwable.cause?.message} **** ")
            println("**** ${throwable.printStackTrace()} **** ")
        }
}

fun <T> resultOf(block: suspend () -> T): Flow<Result<T>> {
    return flow {
        emit(Result.Loading)
        try {
            val result = block()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}

fun <T> execute(
    context: CoroutineContext = Dispatchers.Default,
    block: suspend () -> T
): Flow<Result<*>> {
    return flow {
        emit(Result.Loading)
        try {
            val result = block()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }.flowOn(context)
}