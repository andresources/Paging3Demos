package com.paging.ex3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.paging.ex3.data.APIService
import com.paging.ex3.data.datasource.PostDataSource

class MainViewModel(private val apiService: APIService) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 6)) {
        PostDataSource(apiService)
    }.flow.cachedIn(viewModelScope)
}