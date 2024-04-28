package com.example.passwordmanager.domain.usecases

import com.example.passwordmanager.domain.models.PasswordBuilder
import com.example.passwordmanager.domain.models.PasswordGenerationConfiguration


class GeneratePasswordByConfigurationUseCase(
    private val configuration: PasswordGenerationConfiguration,
) {

    operator fun invoke(): String {
        val password = PasswordBuilder.build(configuration)
        return password.content
    }
}