package com.example.passwordmanager.presentation.states

data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val passwords: List<PasswordUiState> = emptyList()
)
