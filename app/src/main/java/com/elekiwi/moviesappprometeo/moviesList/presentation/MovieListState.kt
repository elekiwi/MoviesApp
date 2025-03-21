package com.elekiwi.moviesappprometeo.moviesList.presentation

import com.elekiwi.moviesappprometeo.core.domain.models.Movie

data class MovieListState(
val isLoading: Boolean = false,
val movieList: List<Movie> = emptyList()
)
