package com.example.pokeapp.data.models

import com.google.gson.annotations.SerializedName

data class PokeListResponse(
    @SerializedName("count")
    val count: Long? = null,

    @SerializedName("next")
    val next: String?  = null,

    @SerializedName("previous")
    val previous: String?  = null,

    @SerializedName("results")
    val results: List<SinglePokeResponse>?  = null,
)