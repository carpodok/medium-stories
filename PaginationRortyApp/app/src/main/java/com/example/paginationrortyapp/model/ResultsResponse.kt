package com.example.paginationrortyapp.model

import com.google.gson.annotations.SerializedName

data class ResultsResponse(

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,
)