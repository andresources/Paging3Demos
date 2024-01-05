package com.paging.ex1

import android.util.Log
import androidx.paging.PagingSource
import com.paging.ex1.data.MyApi
import com.paging.ex1.data.Passenger

class PassengersDataSource(
    private val api: MyApi
) : PagingSource<Int, Passenger>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Passenger> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = api.getPassengersData(nextPageNumber)
            LoadResult.Page(
                data = response.data,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.totalPages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}