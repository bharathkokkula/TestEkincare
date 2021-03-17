package com.example.testproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponceData ( @SerializedName("messages") var messages: List<Message?>? = null,
                          @SerializedName("consultation_request") var consultation_request: Consultationrequest? = null) : Parcelable