package com.sergediame.gozembusinesscase.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import com.sergediame.domain.Result


fun <T, R> UiState<T>.mapSuccess(transform: (T) -> R): UiState<R> {
    return when (this) {
        UiState.Loading -> UiState.Loading
        is UiState.Success -> UiState.Success(transform(data))
        is UiState.Error -> UiState.Error(message)
        is UiState.Empty -> UiState.Empty(message)
        is UiState.Initial -> UiState.Initial(transform(data))
    }
}

sealed interface UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>
    data class Initial<T>(val data: T) : UiState<T>
    data class Error(val message: String?) : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Empty(val message: String?) : UiState<Nothing>
}

fun <T> Flow<T>.asUiState(
    errorMessage: String? = null
): Flow<UiState<T>> {
    return this
        .map<T, UiState<T>> {
            UiState.Success(it)
        }
        .catch { emit(UiState.Error(errorMessage ?: it.message)) }
}

fun <T> Flow<List<T>>.asUiState(
    emptyMessage: String? = null,
    errorMessage: String? = null
): Flow<UiState<List<T>>> {
    return this
        .map {
            if (it.isEmpty()) {
                UiState.Empty(emptyMessage)
            } else {
                UiState.Success(it)
            }
        }
        .catch { emit(UiState.Error(errorMessage ?: it.message)) }
}


fun <T> Flow<Result<T>>.asUiState(): Flow<UiState<T>> {

    return this.map { result ->
        when(result){
            is Result.Error -> UiState.Error(result.exception?.message)
            Result.Loading -> UiState.Loading
            is Result.Success -> UiState.Success(result.data)
        }
    }

}

inline fun <T, R> Flow<T>.mapFlow(crossinline transform: suspend (T) -> R): Flow<R> {
    return this.map { input ->
        transform(input)
    }
}


