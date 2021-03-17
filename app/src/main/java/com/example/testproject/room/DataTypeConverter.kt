package com.example.testproject.room

import androidx.room.TypeConverter
import com.example.testproject.model.Payload
import com.example.testproject.model.User
import com.google.gson.Gson

class DataTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun getplaydata(data: String?): Payload {
        return gson.fromJson(data, Payload::class.java)
    }

    @TypeConverter
    fun storeplaydata(pladata: Payload?): String {
        return gson.toJson(pladata)
    }

    @TypeConverter
    fun getuser(data: String?): User {
        return gson.fromJson(data, User::class.java)
    }

    @TypeConverter
    fun storeuser(glass: User?): String {
        return gson.toJson(glass)
    }
}