package com.skanderjabouzi.rvezytest.presentation

enum class CatStatus { LOADING, ERROR, DONE }

sealed class ResultState {
    data class Success(val data: Any) : ResultState()
    data class Error(val error: String) : ResultState()
    class Unknown : ResultState()
}