package com.example.nolib.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nolib.network.HttpHelper
import com.example.nolib.databinding.ActivityMainBinding
import com.example.nolib.model.LoginRequest
import com.example.nolib.utils.Constant
import org.json.JSONException
import org.json.JSONObject

import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

}

