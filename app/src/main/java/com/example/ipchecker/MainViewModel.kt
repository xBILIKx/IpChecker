package com.example.ipchecker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ipchecker.model.IIpRequest
import com.example.ipchecker.model.IpObj
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainViewModel: ViewModel() {
    private val _ipLiveData = MutableLiveData<String>()
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData
    val ipLiveData: LiveData<String>
        get() = _ipLiveData


    fun getIp(){

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://awstest-balancer-1233234915.us-east-2.elb.amazonaws.com/awstest-service/")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                _errorLiveData.postValue(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val responseString: String = response.body!!.string()

                    val jsonData = JSONObject(responseString)
                    _ipLiveData.postValue(jsonData.getString("ip"))
                }
            }
        })
    }
}