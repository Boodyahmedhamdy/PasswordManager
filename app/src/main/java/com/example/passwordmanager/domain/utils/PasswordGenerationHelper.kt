package com.example.passwordmanager.domain.utils

import com.example.passwordmanager.domain.models.PasswordGenerationConfiguration

fun generatePasswordFromConfigurationAndCharSet(
    charSet: String, configuration: PasswordGenerationConfiguration
): String {
    var password = ""
    while(password.length < configuration.length) {
        password += charSet.random()
    }
    return password
}

fun getCharSetFromConfiguration(configuration: PasswordGenerationConfiguration): String {
    val letters = "abcdefghijklmnopqrstuvwxyz"
    val capitalLetters: String =
        if (configuration.caseSensitive) "ABCDEFGHIJKLMNOPQRSTUVWXYZ" else ""
    val numbers: String =
        if (configuration.hasNumbers) "0123456789" else ""
    val specialCharacters =
        if (configuration.hasSpecialCharacters) "~!@#$%^&*()_+=-`.,<>" else ""

    return "${letters}${capitalLetters}${numbers}${specialCharacters}"
}