package com.paging.ex4.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.paging.ex4.model.DoggoImagePagingSource
import com.paging.ex4.api.ApiService
import com.paging.ex4.model.DoggoImageModel
import kotlinx.coroutines.flow.Flow

/**
 * Now we need to configure and return PagingData with the
 * help of Pager class. To do it let’s create a
 * DoggoImagesRepository class. Inside this class
 * let’s define a function that returns the reactive
 * stream of PagingData<DoggoImageModel> using Pager class.
 */
class DoggoImagesRepository(private val apiService: ApiService = RemoteInjector.injectDoggoApiService()) {

    fun letDoggoImagesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<DoggoImageModel>> {
       return Pager(
           config = pagingConfig,
           pagingSourceFactory = { DoggoImagePagingSource(apiService) }
       ).flow
    }

    private fun getDefaultPageConfig(): PagingConfig{
        return PagingConfig(pageSize = 10, enablePlaceholders = false)
    }

    companion object{
        const val DEFAULT_PAGE_INDEX = 1
        fun getInstance() = DoggoImagesRepository()
    }
}