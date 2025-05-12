package com.example.capstone.network

import com.example.capstone.data.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object ApiClient {
    private const val BASE_URL = "http://192.168.1.16:8000/api/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }



    fun getApiService(tokenProvider: () -> String?): ApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenProvider)) // Use lambda
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }


    // Fallback/default service without token
    val apiService: ApiService by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}

