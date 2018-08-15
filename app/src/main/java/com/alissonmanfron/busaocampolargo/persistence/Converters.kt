package com.alissonmanfron.busaocampolargo.persistence

import android.arch.persistence.room.TypeConverter

class Converters {

    @TypeConverter
    fun stringToArray(values: ArrayList<String>?): String? {
        return values.toString().replace(" ", "").replace("[", "").replace("]", "")
    }

    @TypeConverter
    fun arrayToString(string: String?): ArrayList<String>? {
        val result = ArrayList<String>()
        val values = string?.split(",")
        if (values != null) {
            for (value in values) {
                result.add(value)
            }
        }
        return result
    }

}