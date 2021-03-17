package com.example.testproject.retrofit

import com.example.testproject.model.ResponceData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiHelper {
    @GET("/api/json/get/bTTptqjGnC?indent=2")
    fun getdata() : Call<ResponceData>
}