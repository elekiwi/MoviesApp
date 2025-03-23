package com.elekiwi.moviesappprometeo.addMovie.presentation

import com.elekiwi.moviesappprometeo.core.domain.models.Movie

data class AddMovieState(
    val movieId: Int = -1,
    val title: String = "",
    val description: String = "",
    val comment: String = "",
    val movie: Movie? = null
)
