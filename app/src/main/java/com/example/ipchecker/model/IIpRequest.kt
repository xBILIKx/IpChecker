package com.example.ipchecker.model

import retrofit2.Call
import retrofit2.http.GET

interface IIpRequest {
    @GET("awstest-service")
    fun getIp(): Call<IpObj>
}
