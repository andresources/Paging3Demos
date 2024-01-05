package com.paging.ex3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paging.ex3.data.APIService

class MainViewModelFactory(private val apiService: APIService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}