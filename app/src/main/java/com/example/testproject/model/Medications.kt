package com.example.testproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Medications(
    @SerializedName("number_of_times") var number_of_times: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("duration_unit") var duration_unit: String? = null,
    @SerializedName("dose_quantity") var dose_quantity: Int? = null,
    @SerializedName("frequency") var frequency: String? = null,
    @SerializedName("duration") var duration: Int? = null,
    @SerializedName("number_of_days") var number_of_days: Int? = null,
    @SerializedName("dose_unit") var dose_unit: String? = null,
    @SerializedName("instructions") var instructions: String? = null
) : Parcelable
