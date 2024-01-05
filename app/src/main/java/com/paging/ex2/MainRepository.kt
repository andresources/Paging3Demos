package com.paging.ex2

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.paging.ex1.PassengersDataSource
import kotlinx.coroutines.flow.Flow

class MainRepository constructor(private val retrofitService: RetrofitService = RetrofitService.getInstance()) {

    fun getAllMovies(): LiveData<PagingData<Movie>> {

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                MoviePagingSource(retrofitService)
            }
            , initialKey = 1
        ).liveData
    }
    fun getMV() : Flow<PagingData<Movie>> =
     Pager(PagingConfig(pageSize = NETWORK_PAGE_SIZE)) {
        MoviePagingSource(retrofitService)
    }.flow

}