package com.example.nolib.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nolib.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "")
        binding.textView.text = token.toString()
    }
}