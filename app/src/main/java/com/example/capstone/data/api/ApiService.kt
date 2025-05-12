package com.example.capstone.data.api

import com.example.capstone.data.models.CreateRequest
import com.example.capstone.data.models.LoginRequest
import com.example.capstone.data.models.LoginResponse
import com.example.capstone.data.models.RegisterRequest
import com.example.capstone.data.models.RegisterResponse
import com.example.capstone.data.models.RequestResponse
import com.example.capstone.data.models.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("requests")
    suspend fun createRequest(@Body request: CreateRequest): Response<CreateRequest>

    @GET("requests")
    suspend fun getRequests(): Response<RequestResponse>

    @GET("profile")
    suspend fun getProfile(): Response<User>

    @POST("logout")
    suspend fun logout(): Response<Map<String, String>>

}