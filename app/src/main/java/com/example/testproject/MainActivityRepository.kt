package com.example.testproject

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.testproject.model.ResponceData
import com.example.testproject.retrofit.ApiClient
import com.example.testproject.room.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {
    val serviceSetterGetter = MutableLiveData<ResponceData>()
    fun getServicesApiCall(): MutableLiveData<ResponceData> {

        val call = ApiClient.setBaseUrl().getdata()
        call.enqueue(object: Callback<ResponceData> {
            override fun onFailure(call: Call<ResponceData>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }
            override fun onResponse(
                call: Call<ResponceData>,
                response: Response<ResponceData>
            ) {
                val data = response.body()
                serviceSetterGetter.value = data
            }
        })
        return serviceSetterGetter
    }

}