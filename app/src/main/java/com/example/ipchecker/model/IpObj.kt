package com.example.ipchecker.model

import com.google.gson.annotations.SerializedName

data class IpObj(
    @SerializedName("ip")
    val ip: String,
    @SerializedName("xForwardedFor")
    val xForwardedFor: String
)
