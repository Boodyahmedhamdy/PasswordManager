package com.example.passwordmanager.domain.models

import com.example.passwordmanager.domain.utils.generatePasswordFromConfigurationAndCharSet
import com.example.passwordmanager.domain.utils.getCharSetFromConfiguration

class PasswordBuilder {
    companion object {
        fun build(configuration: PasswordGenerationConfiguration): Password {
            val characters = getCharSetFromConfiguration(configuration)
            val passwordContent = generatePasswordFromConfigurationAndCharSet(
                characters, configuration
            )
            return Password(content = passwordContent)
        }
    }
}

