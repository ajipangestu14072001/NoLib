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

        binding.btn.setOnClickListener {
            thread {
//                val response = HttpHelper.sendHttpRequest(Constant.BASE_URL + "user/2", "GET", null)
//                runOnUiThread {
//                    println("INI $response")
//                }
                val loginRequest = LoginRequest("eve.holt@reqres.in", "cityslicka")
                val postData = JSONObject()
                postData.put("email", loginRequest.email)
                postData.put("password", loginRequest.password)
                val response =
                    HttpHelper.sendHttpRequest(Constant.BASE_URL + "login", "POST", postData, null)
                runOnUiThread {
                    val intent = Intent(applicationContext, MainActivity2::class.java)
                    val sharedPref = getSharedPreferences("myKey", MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    try {
                        val jsonObject = JSONObject(response)
                        val token = jsonObject.getString("token")
                        editor.putString("token", token)
                        println("Token: $token")
                    } catch (e: JSONException) {
                        println("Failed to parse token")
                    }
                    editor.apply()
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

}

