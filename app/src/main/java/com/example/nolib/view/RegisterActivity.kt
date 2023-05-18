package com.example.nolib.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.nolib.R
import com.example.nolib.databinding.ActivityRegisterBinding
import com.example.nolib.model.LoginRequest
import com.example.nolib.model.RegisterRequest
import com.example.nolib.network.HttpHelper
import com.example.nolib.utils.Constant
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import kotlin.concurrent.thread

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.signUp.setOnClickListener {
            val username = binding.name.text.toString().trim()
            val email = binding.mail.text.toString().trim()
            val password = binding.pwd.text.toString().trim()
            try {
                register(username, password, password)
                Toast.makeText(applicationContext, username + email + password, Toast.LENGTH_SHORT)
                    .show()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Something When Wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun register(name: String, email: String, password: String) {
        thread {
            val registerRequest = RegisterRequest(name, email, password)
            val postData = JSONObject()
            postData.put("email", registerRequest.email)
            postData.put("password", registerRequest.password)
            val response = HttpHelper.sendHttpRequest(Constant.BASE_URL + "register", "POST", postData, null)
            runOnUiThread {
                try {
                    val jsonObject = JSONObject(response)
                    val token = jsonObject.getString("token")
                    println("Success to parse token $token")
                } catch (e: JSONException) {
                    println("Failed to parse token")
                }
            }
        }
    }
}