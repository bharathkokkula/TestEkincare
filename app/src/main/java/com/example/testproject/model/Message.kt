package com.example.testproject.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.testproject.room.DataTypeConverter
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "messages")
data class Message(
    @SerializedName("payload")
    @TypeConverters(DataTypeConverter::class)
    var payload: Payload? = null,

    @SerializedName("customer_id")
    var customer_id: String? = null,

    @SerializedName("uniqueId")
    var uniqueId: Long = 0,

    @SerializedName("user")@TypeConverters(DataTypeConverter::class)
    var user: User? = null,

    @SerializedName("transient")
    val transients: Boolean = false,

    @SerializedName("channel_id")
    var channel_id: String? = null,

    @SerializedName("message_id")
    var message_id: String? = null,

    @SerializedName("intentName")
    var intentName: String? = null,

    @SerializedName("consultation_request_id")
    var consultation_request_id: Int = 0,

    @SerializedName("timestamp")
    var timestamp: Long = 0,

    @PrimaryKey(autoGenerate = true)
    var autonumber: Long = 0,

    @SerializedName("status")
    var status: String? = null
) : Parcelable
