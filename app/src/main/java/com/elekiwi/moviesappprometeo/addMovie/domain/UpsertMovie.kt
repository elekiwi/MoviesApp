package com.elekiwi.moviesappprometeo.addMovie.domain

import android.util.Log
import com.elekiwi.moviesappprometeo.core.domain.models.Movie
import com.elekiwi.moviesappprometeo.core.domain.repositories.MovieListRepository

class UpsertMovie(
    private val repository: MovieListRepository
) {

    suspend operator fun invoke(
        movie: Movie
    ): Boolean {
        Log.e("LeoDebug5", "invoke: ${movie.title}", )

        if (movie.title.isEmpty()) {
            return false
        }

        if (movie.description.isNullOrEmpty()) {
            Log.e("LeoDebug4", "invoke: ${movie.description}", )

            return false
        }

        Log.e("LeoDebug3", "invoke: ", )
        val updateMovie = Movie(
            movie.title,
            movie.description,
            movie.poster,
            movie.time,
            movie.trailer,
            movie.imdb,
            movie.year,
            movie.price,
            movie.isSeen,
            movie.toSee,
            movie.comments,
            movie.rating,
            movie.id
        )
        return try {
            repository.upsertMovie(updateMovie)
            true
        } catch (e: Exception) {
            false
        }

    }
}