package com.example.paginationrortyapp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paginationrortyapp.model.ResultsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AppRepository @Inject constructor(private val appService: AppService) {
    fun fetchResponseList(): Flow<PagingData<ResultsResponse>> {

        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGER_LIST_SIZE
            ),
            pagingSourceFactory = { DataPagingSource(appService) }
        ).flow.flowOn(Dispatchers.IO)
    }
}