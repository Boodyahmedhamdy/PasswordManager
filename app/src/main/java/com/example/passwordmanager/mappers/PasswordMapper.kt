package com.example.passwordmanager.mappers

import com.example.passwordmanager.data.PasswordEntity
import com.example.passwordmanager.domain.models.Password
import com.example.passwordmanager.presentation.states.PasswordUiState

fun PasswordUiState.toDomain(): Password {
    return Password(
        id = id,
        label = label,
        content = content,
        imagePath = imagePath
    )
}

fun Password.toUiState(): PasswordUiState {
    return PasswordUiState(
        id, content, label, imagePath
    )
}

fun Password.toEntity(): PasswordEntity {
    return PasswordEntity(
        id, label, content, imagePath
    )
}

fun PasswordEntity.toDomain(): Password {
    return Password(
        id, label, content, imagePath
    )
}