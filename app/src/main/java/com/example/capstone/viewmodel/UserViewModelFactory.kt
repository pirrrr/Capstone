package com.example.capstone.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.repository.UserRepository


class UserViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repo = UserRepository(context)  // ✅ pass context correctly
        return UserViewModel(repo) as T
    }
}