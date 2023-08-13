package com.example.pokeapp.data.repositories

import com.example.pokeapp.data.datasources.PokeService
import com.example.pokeapp.data.models.PokeListResponse
import com.example.pokeapp.utils.Constants
import com.example.pokeapp.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokeRepository @Inject constructor(private val pokeService: PokeService) {

    suspend fun fetchPokemonData(): Flow<ViewState<PokeListResponse>> {
        return flow {

            val comment = pokeService.getPokemons()

            emit(ViewState.success(comment))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchPokemonsByOffset(offset: Int): Flow<ViewState<PokeListResponse>> {
        return flow {

            val comment =
                pokeService.getPokemonsByOffset(limit = Constants.LIMIT_POKEMONS, offset = offset)

            emit(ViewState.success(comment))
        }.flowOn(Dispatchers.IO)
    }

}