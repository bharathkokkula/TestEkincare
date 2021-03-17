package com.example.testproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payload ( @SerializedName("text") var text: String? = null,
                     @SerializedName("file_name") var file_name: String? = null,
                     @SerializedName("file_url") var file_url: String? = null,
                     @SerializedName("recommended_tests") var recommended_tests: ArrayList<Recommended_tests>? = null,
                     @SerializedName("medications") var medications: ArrayList<Medications>? = null
        ) : Parcelable