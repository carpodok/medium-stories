package com.example.pokeapp.data.datasources

import com.example.pokeapp.data.models.PokeListResponse
import com.example.pokeapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeService {

    @GET(Constants.END_POINT_POKEMONS)
    suspend fun getPokemons(): PokeListResponse

    @GET("pokemon/")
    suspend fun getPokemonsByOffset(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokeListResponse

}