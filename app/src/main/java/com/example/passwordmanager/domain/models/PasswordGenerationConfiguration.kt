package com.example.passwordmanager.domain.models

data class PasswordGenerationConfiguration(
    val length: Int = 16,
    val caseSensitive: Boolean = true,
    val hasNumbers: Boolean = true,
    val hasSpecialCharacters: Boolean = true,
)
