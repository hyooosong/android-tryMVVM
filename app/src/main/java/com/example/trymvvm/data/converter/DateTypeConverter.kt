package com.example.trymvvm.data.converter

import androidx.room.TypeConverter
import java.util.*


class DateTypeConverter {

    @TypeConverter
    fun fromDate(date: Date?) : Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millis: Long?) : Date? {
        return Date(millis ?: return null)
    }
}