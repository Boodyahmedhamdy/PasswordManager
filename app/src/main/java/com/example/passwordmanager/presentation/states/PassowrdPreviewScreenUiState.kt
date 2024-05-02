package com.example.passwordmanager.presentation.states


data class PasswordPreviewScreenUiState(
    val showAlertDialog: Boolean = false,
    val password: PasswordUiState = PasswordUiState()
)