package com.olr261dn.clock.components.actionState

sealed class Result<out T> {
    data object Loading: Result<Nothing>()
    data class Success<T>(val data: T): Result<T>()
    data class Error(val errorMessage: String): Result<Nothing>()
}