package com.frontendsource.shoestore.sealed

import com.frontendsource.shoestore.sealed.Error as ErrorBody

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    object Success : UiState()
    class Error(val error: ErrorBody) : UiState()
}
