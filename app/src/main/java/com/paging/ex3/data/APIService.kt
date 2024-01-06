package com.paging.ex3.data

import com.paging.ex3.data.response.ApiResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("api/users")
    suspend fun getListData(@Query("page") pageNumber: Int,@Query("per_page") size: Int = 10): Response<ApiResponse>

    companion object {
        fun getApiService() = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
}