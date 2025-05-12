package com.example.capstone.network

import android.content.Context

fun getTokenProvider(context: Context): () -> String? = {
    val prefs = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    prefs.getString("auth_token", null)
}
