package com.example.noteapp.data.database

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun DateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun LongToDate(long: Long): Date {
        return Date(long)
    }
}