package com.example.passwordmanager.data.converters

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun fromTimestamp(timeStamp: Long): Date {
        return Date(timeStamp)
    }
    @TypeConverter
    fun toTimestamp(date: Date): Long {
        return date.time
    }
}