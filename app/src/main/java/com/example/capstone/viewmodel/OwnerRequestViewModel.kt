package com.example.capstone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.models.Request
import com.example.capstone.repository.RequestRepository
import kotlinx.coroutines.launch

class OwnerRequestViewModel(private val repository: RequestRepository) : ViewModel() {

    private val _ownerRequests = MutableLiveData<List<Request>>()
    val ownerRequests: LiveData<List<Request>> = _ownerRequests

    fun fetchOwnerRequests() {
        viewModelScope.launch {
            try {
                val response = repository.getOwnerRequests()
                if (response.isSuccessful && response.body() != null) {
                    _ownerRequests.value = response.body()!!.requests
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
