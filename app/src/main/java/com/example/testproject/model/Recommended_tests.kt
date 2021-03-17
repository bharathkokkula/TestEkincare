package com.example.testproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recommended_tests(
    @SerializedName("package_id") var package_id: Int? = null,
    @SerializedName("number_of_tests") var number_of_tests: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null
) : Parcelable