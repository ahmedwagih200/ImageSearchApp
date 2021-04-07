package com.example.imagesearchapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.imagesearchapp.api.PhotoApi
import com.example.imagesearchapp.data.PhotoPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val photoApi: PhotoApi) {
    fun getSearchResults(query: String ) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {PhotoPagingSource(photoApi, query)}
        ).liveData
}
