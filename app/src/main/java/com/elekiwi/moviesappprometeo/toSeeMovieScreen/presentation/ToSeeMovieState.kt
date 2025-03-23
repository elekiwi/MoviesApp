package com.elekiwi.moviesappprometeo.toSeeMovieScreen.presentation

import com.elekiwi.moviesappprometeo.core.domain.models.Movie

data class ToSeeMovieState(
    val isLoading: Boolean = false,
    val movieList: List<Movie> = emptyList()
)
