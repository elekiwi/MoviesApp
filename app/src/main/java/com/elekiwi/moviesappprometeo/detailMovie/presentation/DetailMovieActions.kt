package com.elekiwi.moviesappprometeo.detailMovie.presentation

interface DetailMovieActions {
    data class UpdateToSee(val isToSee: Boolean): DetailMovieActions
    data class UpdateComment(val comment: String): DetailMovieActions
    data class UpdateRating(val rating: Int): DetailMovieActions
    data class LoadMovie(val id: Int): DetailMovieActions
}