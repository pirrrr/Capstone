package com.example.capstone.repository

import com.example.capstone.data.models.LoginRequest
import com.example.capstone.data.models.LoginResponse
import com.example.capstone.data.models.RegisterRequest
import com.example.capstone.data.models.RegisterResponse
import com.example.capstone.data.models.User
import com.example.capstone.network.ApiClient
import retrofit2.Response

class UserRepository {

    suspend fun registerUser(registerRequest: RegisterRequest): Response<RegisterResponse> {
        return ApiClient.apiService.register(registerRequest)
    }

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> {
        return ApiClient.apiService.login(loginRequest)
    }

    suspend fun getProfile(token: String): Response<User> {
        return ApiClient.apiService.getProfile("Bearer $token")
    }

    suspend fun logout(token: String): Response<Map<String, String>> {
        return ApiClient.apiService.logout("Bearer $token")
    }
}