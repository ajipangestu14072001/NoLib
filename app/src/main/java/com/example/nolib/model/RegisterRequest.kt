package com.example.nolib.model

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)