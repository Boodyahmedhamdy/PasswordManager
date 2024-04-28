package com.example.passwordmanager.domain.models

import javax.inject.Inject

data class PasswordGenerationConfiguration(
    val length: Int = 16,
    val caseSensitive: Boolean = true,
    val hasNumbers: Boolean = true,
    val hasSpecialCharacters: Boolean = true,
)
