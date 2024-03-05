package com.adel.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject
@ProvidedTypeConverter
class JsonConverter @Inject constructor(val gson: Gson) {
    @TypeConverter
    inline fun<reified T> jsonToObject(json: String?): T {
        val type: Type = object : TypeToken<T>() {}.type
        return gson.fromJson(json, type)
    }
    @TypeConverter
    fun<T> T.objectToJson(): String? {
        val type: Type = object : TypeToken<T>() {}.type
        return gson.toJson(this, type)
    }
}