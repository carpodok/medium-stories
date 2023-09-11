package com.example.paginationrortyapp.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(

    @SerializedName("info")
    val info : InfoResponse,

    @SerializedName("results")
    val results : List<ResultsResponse>
)