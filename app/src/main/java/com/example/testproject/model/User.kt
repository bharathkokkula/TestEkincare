package com.example.testproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User ( @SerializedName("type") var type: String? = null) : Parcelable