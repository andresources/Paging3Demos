package com.paging.ex1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paging.ex1.data.MyApi


@Suppress("UNCHECKED_CAST")
class PassengersViewModelFactory(
    private val api: MyApi
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PassengersViewModel(api) as T
    }
}