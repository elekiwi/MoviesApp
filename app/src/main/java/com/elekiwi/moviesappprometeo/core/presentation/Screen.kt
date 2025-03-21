package com.elekiwi.moviesappprometeo.core.presentation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object MovieList: Screen
    @Serializable
    data class DetailMovie(val movieId: Int = -1): Screen
    @Serializable
    data class AddMovie(val movieId: Int = -1): Screen
    @Serializable
    data object ToSeeMovie: Screen
}