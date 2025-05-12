package com.example.capstone.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.models.CreateRequest
import com.example.capstone.data.models.Request
import com.example.capstone.repository.RequestRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class RequestViewModel(private val requestRepository: RequestRepository) : ViewModel() {

    private val _submitResult = MutableLiveData<CreateRequest?>()
    val submitResult: LiveData<CreateRequest?> = _submitResult

    fun submitRequest(request: CreateRequest) {
        viewModelScope.launch {
            try {
                val response = requestRepository.createRequest(request)

                if (response.isSuccessful) {
                    _submitResult.postValue(response.body())
                } else {
                    _submitResult.postValue(null)
                }
            } catch (e: Exception) {
                _submitResult.postValue(null)
            }
        }
    }
}