package com.example.ipchecker

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm = ViewModelProvider(this)[MainViewModel::class.java]

        vm.errorLiveData.observe(this, {
            vm.clearError()
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()

        })

        vm.ipLiveData.observe(this, {
            findViewById<TextView>(R.id.ipTextView).text = "IP: $it"
        })

        findViewById<Button>(R.id.myIpButton).setOnClickListener {
            vm.getIp()
        }
    }
}