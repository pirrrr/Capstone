package com.example.capstone.data.models

import java.math.BigInteger

data class LoginResponse(
    val user: User,
    val token: String,
)
