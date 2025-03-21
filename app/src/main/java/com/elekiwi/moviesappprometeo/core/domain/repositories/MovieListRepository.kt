package com.elekiwi.moviesappprometeo.core.domain.repositories

import com.elekiwi.moviesappprometeo.core.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    suspend fun upsertMovie(movie: Movie)
    suspend fun insertAllMovies(movies: List<Movie>)
    suspend fun getMovieById(id: Int): Movie?
    fun getAllMovies(): Flow<List<Movie>>
    fun clearMovies()

}