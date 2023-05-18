package com.example.nolib.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.nolib.databinding.ActivityLoginBinding
import com.example.nolib.model.LoginRequest
import com.example.nolib.network.HttpHelper
import com.example.nolib.utils.Constant
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import kotlin.concurrent.thread

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.login.setOnClickListener {
            val username = binding.mail.text.toString().trim()
            val password = binding.pwd.text.toString().trim()
            try{
                login(username, password)
            }catch (e: Exception){
                Toast.makeText(applicationContext,"Something When Wrong" ,Toast.LENGTH_SHORT).show()
            }
        }

        binding.signUp.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }
    }

    private fun login(username: String, password: String) {
        thread {
            val loginRequest = LoginRequest(username, password)
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