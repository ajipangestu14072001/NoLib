package com.example.nolib.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.nolib.databinding.ActivityLauncherBinding

class LauncherActivity : AppCompatActivity() {
    lateinit var binding : ActivityLauncherBinding
    private val splashTimeOut: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@LauncherActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, splashTimeOut)
    }
}