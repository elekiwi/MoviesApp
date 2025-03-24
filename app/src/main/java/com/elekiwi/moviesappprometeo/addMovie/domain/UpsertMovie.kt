package com.elekiwi.moviesappprometeo.addMovie.domain

import com.elekiwi.moviesappprometeo.core.domain.models.Movie
import com.elekiwi.moviesappprometeo.core.domain.repositories.MovieListRepository
import java.util.UUID

class UpsertMovie(
    private val repository: MovieListRepository
) {

    suspend operator fun invoke(
        movie: Movie
    ): Boolean {

        if (movie.title.isEmpty()) {
            return false
        }

        if (movie.description.isNullOrEmpty()) {
            return false
        }

        val movieWithId = if (movie.id == -1) {
            movie.copy(id = generateMovieId())
        } else {
            movie
        }

        val updateMovie = Movie(
            movieWithId.title,
            movieWithId.description,
            movieWithId.poster,
            movieWithId.time,
            movieWithId.trailer,
            movieWithId.imdb,
            movieWithId.year,
            movieWithId.price,
            movieWithId.isSeen,
            movieWithId.toSee,
            movieWithId.comments,
            movieWithId.rating,
            movieWithId.id
        )
        return try {
            repository.upsertMovie(updateMovie)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun generateMovieId(): Int {
        return UUID.randomUUID().toString().hashCode()
    }
}