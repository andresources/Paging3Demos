package com.paging.ex2

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.paging.ex1.PassengersDataSource
import kotlinx.coroutines.flow.Flow

class MainRepository constructor(private val retrofitService: RetrofitService = RetrofitService.getInstance()) {
    /*fun getAllMovies(): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 1
            ),
            pagingSourceFactory = {
                MoviePagingSource(retrofitService)
            }
            , initialKey = 1
        ).liveData
    }*/
    /*fun getAllMovies(): LiveData<PagingData<Movie>>
     = Pager(config = PagingConfig(pageSize = 10)) {
        MoviePagingSource(retrofitService)
    }.liveData*/

    fun getAllMovies(): LiveData<PagingData<Movie>>
     = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { MoviePagingSource(retrofitService) },
    ).liveData

    fun getMV(): Flow<PagingData<Movie>>
            = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { MoviePagingSource(retrofitService) },
    ).flow

}