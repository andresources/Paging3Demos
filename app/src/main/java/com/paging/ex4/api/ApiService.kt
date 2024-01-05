package com.paging.ex4.api

import com.paging.ex4.model.DoggoImageModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/images/search")
    suspend fun getDoggoImages(@Query("page") page: Int, @Query("limit") size: Int  =10): List<DoggoImageModel>
}