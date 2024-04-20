package com.example.passwordmanager.domain.models

data class Password(
    val id: Int = 0,
    val label: String = "",
    val content: String = "",
    val imagePath: String = ""
)
