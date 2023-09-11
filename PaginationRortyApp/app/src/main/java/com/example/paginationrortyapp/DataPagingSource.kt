package com.example.paginationrortyapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationrortyapp.model.ResultsResponse

class DataPagingSource(private val apiService: AppService) :
    PagingSource<Int, ResultsResponse>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsResponse> {

        return try {
            val position = params.key ?: Constants.STARTING_PAGE_INDEX
            val response = apiService.getResponse(page = position)
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, ResultsResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

