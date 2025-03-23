package com.elekiwi.moviesappprometeo.detailMovie.presentation

import com.elekiwi.moviesappprometeo.core.domain.models.Movie

data class DetailMovieState(
    val movieId: Int = -1,
    val isToSee: Boolean = false,
    val comment: String = "",
    val rating: Int = 0,
    val movie: Movie? = null
)
