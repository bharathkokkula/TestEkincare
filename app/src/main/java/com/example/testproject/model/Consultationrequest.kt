package com.example.testproject.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "consultationreq")
data class Consultationrequest(
    @SerializedName("chief_complaint") var chief_complaint: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("active")
    var active: Boolean? = null,
    @SerializedName("customer_id")
    var customer_id: Int? = null,
    @SerializedName("doctor_id") @PrimaryKey
    var doctor_id: Int? = null,
    @SerializedName("free")
    var free: Boolean? = null,
    @SerializedName("request_origin")
    var request_origin: String? = null,
    @SerializedName("request_status")
    var request_status: String? = null,
    @SerializedName("doctor_name")
    var doctor_name: String? = null,
    @SerializedName("updated_at")
    var updated_at: String? = null,
    @SerializedName("created_at")
    var created_at: String? = null,
    @SerializedName("consent")
    var consent: Boolean? = null,
    @SerializedName("initiator_id")
    var initiator_id: Int? = null,
    @SerializedName("request_type")
    var request_type: String? = null
) : Parcelable