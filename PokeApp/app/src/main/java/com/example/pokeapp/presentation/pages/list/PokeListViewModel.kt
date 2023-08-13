package com.example.pokeapp.presentation.pages.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.data.models.PokeListResponse
import com.example.pokeapp.data.repositories.PokeRepository
import com.example.pokeapp.utils.Status
import com.example.pokeapp.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(private val pokeRepository: PokeRepository) :
    ViewModel() {

    val pokemonsState = MutableStateFlow(
        ViewState(
            Status.LOADING,
            PokeListResponse(), ""
        )
    )


    init {
        fetchPokemons()
    }

    fun fetchDataByOffset(offset: Int) {

        pokemonsState.value = ViewState.loading()

        viewModelScope.launch {


            pokeRepository.fetchPokemonsByOffset(offset)

                .catch {
                    pokemonsState.value =
                        ViewState.error(it.message.toString())
                }
                .collect { pokemonsResponseViewState ->
                    pokemonsState.value = ViewState.success(pokemonsResponseViewState.data)
                }
        }
    }

    fun fetchPokemons() {

        pokemonsState.value = ViewState.loading()

        viewModelScope.launch {


            pokeRepository.fetchPokemonData()

                .catch {
                    pokemonsState.value =
                        ViewState.error(it.message.toString())
                }

                .collect {
                    pokemonsState.value = ViewState.success(it.data)
                }
        }
    }
}