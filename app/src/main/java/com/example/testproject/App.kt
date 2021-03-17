package com.example.testproject

import android.app.Application
import com.example.testproject.room.AppDatabase

class APp : Application() {
    private val appRunning = false
    override fun onCreate() {
        super.onCreate()
        AppDatabase.invoke(this) //--AppDatabase_Impl does not exist
    }
}