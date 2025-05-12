package com.example.capstone.repository

import android.telecom.Call
import com.example.capstone.data.api.ApiService
import com.example.capstone.data.models.CreateRequest
import com.example.capstone.data.models.Request
import com.example.capstone.data.models.RequestResponse
import com.example.capstone.network.ApiClient
import com.example.capstone.network.ApiClient.apiService
import okhttp3.RequestBody
import retrofit2.Response

class RequestRepository (private val apiService: ApiService) {


    suspend fun createRequest(request: CreateRequest): Response<CreateRequest> {
        return apiService.createRequest(request)
    }

    suspend fun getRequests(): Response<RequestResponse> {
        return apiService.getRequests()
    }

    suspend fun getOwnerRequests(): Response<RequestResponse> {
        return apiService.getOwnerRequests()
    }

}