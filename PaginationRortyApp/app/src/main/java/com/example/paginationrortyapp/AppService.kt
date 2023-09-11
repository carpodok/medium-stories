package com.example.paginationrortyapp

import com.example.paginationrortyapp.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    @GET(Constants.CHARACTER_LIST_END_POINT)
    suspend fun getResponse(@Query("page") page: Int): Response<ResponseModel>
}