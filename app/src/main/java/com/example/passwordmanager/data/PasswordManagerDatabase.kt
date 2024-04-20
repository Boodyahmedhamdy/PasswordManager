package com.example.passwordmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.passwordmanager.data.converters.DateConverter

@Database(entities = [PasswordEntity::class], version=1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class PasswordManagerDatabase: RoomDatabase() {
    abstract fun passwordDAO(): PasswordDAO
}