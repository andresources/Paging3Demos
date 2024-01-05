package com.paging.ex2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.paging.ex1.PassengersDataSource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {
    val mainRepository = MainRepository()
    val errorMessage = MutableLiveData<String>()
    fun getMovieList(): LiveData<PagingData<Movie>> {
        return mainRepository.getAllMovies().cachedIn(viewModelScope)
    }
}