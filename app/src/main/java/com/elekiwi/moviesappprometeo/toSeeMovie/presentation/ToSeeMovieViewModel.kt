package com.elekiwi.moviesappprometeo.toSeeMovie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elekiwi.moviesappprometeo.core.domain.repositories.MovieListRepository
import com.elekiwi.moviesappprometeo.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToSeeMovieViewModel @Inject constructor(
    private val repository: MovieListRepository
): ViewModel() {

    private var _movieListState = MutableStateFlow(ToSeeMovieState())
    val movieListState = _movieListState.asStateFlow()

    fun getMovieList(isToSee: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _movieListState.update {
                it.copy(
                    isLoading = true
                )
            }

            repository.getAllMovies().collectLatest { result ->
                when(result) {
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { list ->
                            _movieListState.update {
                                if (isToSee) {
                                    it.copy(
                                        movieList = list.filter {
                                            it.toSee
                                        }
                                    )
                                } else {
                                    it.copy(
                                        movieList = list.filter {
                                            !it.toSee
                                        }
                                    )
                                }

                            }
                        }
                    }
                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(
                                isLoading = result.isLoading
                            )
                        }
                    }
                }
            }
        }
    }
}