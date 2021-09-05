package com.umesh.hobiapplication.utils

import androidx.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class JsonStringHandler {
    /**
     * Type converter for form list to a json string for storing it in DB
     *
     * @param value
     * @return
     */
    @TypeConverter
    fun fromCountryLangList(value: List<String/*FormField*/>): String {
        val gson = Gson()
        val type = object : TypeToken<List<String/*FormField*/>>() {}.type
        return gson.toJson(value, type)
    }

    /**
     * Type converter to convert json string to list of form
     *
     * @param value
     * @return
     */
    @TypeConverter
    fun toCountryLangList(value: String): List<String/*FormField*/> {
        val gson = Gson()
        val type = object : TypeToken<List<String/*FormField*/>>() {}.type
        return gson.fromJson(value, type)
    }
}