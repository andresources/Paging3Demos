package com.paging.ex3.data.datasource

import androidx.paging.PagingSource
import com.paging.ex3.data.APIService
import com.paging.ex3.data.response.Data

class PostDataSource(private val apiService: APIService) : PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getListData(currentLoadingPageKey)
            val responseData = mutableListOf<Data>()
            val data = response.body()?.data ?: emptyList()
            val total_pages = response.body()!!.total_pages
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey.minus(1)

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = if(currentLoadingPageKey < total_pages) currentLoadingPageKey.plus(1) else null
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}