package com.example.boaviagem

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.*

class DatabaseConverters {

    @TypeConverter
    fun fromTimeStamp(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun bigDecimalToString(input: BigDecimal?): String? {
        return input?.toPlainString()
    }

    @TypeConverter
    fun stringToBigDecimal(input: String?): BigDecimal? {
        if (input == null) {
            return null
        }
        if (input.isEmpty()) return BigDecimal("0.0")
        return input.toBigDecimalOrNull() ?: BigDecimal("0.0")
    }
}