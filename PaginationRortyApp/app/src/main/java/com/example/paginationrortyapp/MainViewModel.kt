package com.example.paginationrortyapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.paginationrortyapp.model.ResultsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appRepository: AppRepository,
) :
    ViewModel() {

    // to make it read-only state variable
    private val _resultState = MutableStateFlow<Flow<PagingData<ResultsResponse>>>(
        value = flowOf(PagingData.empty())
    )
    val resultsState: StateFlow<Flow<PagingData<ResultsResponse>>> = _resultState

    init {
        getCharacterList()
    }

    private fun getCharacterList() {

        viewModelScope.launch {

            val pagerFlow = appRepository.fetchResponseList()
                .catch {

                }
                .map { pagingData ->
                    pagingData.map {

                            resultsResponse: ResultsResponse ->

                        ResultsResponse(
                            id = resultsResponse.id,
                            name = resultsResponse.name,
                        )

                    }
                }
                .cachedIn(viewModelScope)

            _resultState.value = pagerFlow
        }
    }
}