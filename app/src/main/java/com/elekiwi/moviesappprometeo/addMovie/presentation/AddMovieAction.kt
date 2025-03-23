package com.elekiwi.moviesappprometeo.addMovie.presentation

sealed interface AddMovieAction {
    data class UpdateTitle(val newTitle: String) : AddMovieAction
    data class UpdateDescription(val newDescription: String) : AddMovieAction
    data class UpdateComment(val newComment: String) : AddMovieAction
    data object SaveMovie: AddMovieAction
    data class LoadMovie(val id: Int): AddMovieAction

}