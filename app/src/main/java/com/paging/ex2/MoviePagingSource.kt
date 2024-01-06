package com.paging.ex2


import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception

class MoviePagingSource(private val apiService: RetrofitService): PagingSource<Int, Movie>() {

//8fc731ec6c9825af570974c6b95ba272
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        return try {
            val position = params.key ?: 1
            val response = apiService.getTopRatedMovies("e8d648003bd11b5c498674fbd4905525","en-US",position)
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position < response.body()!!.total_pages) position + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}