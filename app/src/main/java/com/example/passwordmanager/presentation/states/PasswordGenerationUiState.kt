package com.example.passwordmanager.presentation.states

data class PasswordGenerationUiState(
    val passwordConfigurationScreenUiState: PasswordConfigurationScreenUiState =
        PasswordConfigurationScreenUiState(),
    val passwordPreviewScreenUiState: PasswordPreviewScreenUiState =
        PasswordPreviewScreenUiState()
)