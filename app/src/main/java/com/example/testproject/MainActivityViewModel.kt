package com.example.testproject

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testproject.model.Consultationrequest
import com.example.testproject.model.Message
import com.example.testproject.model.ResponceData
import com.example.testproject.room.AppDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    var db :AppDatabase ?= null
    var servicesLiveData: MutableLiveData<ResponceData>? = MutableLiveData<ResponceData>()

    fun getmessages() : LiveData<ResponceData>? {
        servicesLiveData = MainActivityRepository.getServicesApiCall()
        return servicesLiveData
    }

    fun savedata(data: ResponceData?) {
        GlobalScope.launch {
            db?.patientDao()?.insertAll(data?.messages)
        }
        GlobalScope.launch {
            db?.patientDetailsDao()?.insert(data?.consultation_request)
        }
    }
    fun appdb(mainActivity: Context) {
         db = Room.databaseBuilder(
            mainActivity,
            AppDatabase::class.java, "testproject.db"
        ).build()
    }

    fun getofflinemessages(): LiveData<ResponceData>? {
        GlobalScope.launch {
          val conreq : Consultationrequest? =   db?.patientDetailsDao()?.getuserone()
            if(conreq!=null) {
                val messages : List<Message?>? = conreq.id?.let { db?.patientDao()?.getmessages(it) }
                val res = ResponceData(messages ,conreq)
                servicesLiveData?.postValue(res)
            }
        }
        return servicesLiveData
    }
}