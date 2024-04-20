package com.example.passwordmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("passwords")
data class PasswordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val label: String = "",
    val content: String = "",
    val imagePath: String = "",

    val createdAt: Date = Date(0L)
)
