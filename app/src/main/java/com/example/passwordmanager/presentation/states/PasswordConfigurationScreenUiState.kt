package com.example.passwordmanager.presentation.states

import com.example.passwordmanager.domain.models.PasswordGenerationConfiguration

data class PasswordConfigurationScreenUiState(
    val configuration: PasswordGenerationConfiguration = PasswordGenerationConfiguration()
)
