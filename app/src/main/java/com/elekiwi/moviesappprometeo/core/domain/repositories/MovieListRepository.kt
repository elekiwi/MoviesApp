package com.elekiwi.moviesappprometeo.core.domain.repositories

import com.elekiwi.moviesappprometeo.core.domain.models.Movie
import com.elekiwi.moviesappprometeo.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    suspend fun upsertMovie(movie: Movie)
    suspend fun insertAllMovies(movies: List<Movie>)
    suspend fun getMovieById(id: Int): Flow<Resource<Movie>>
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    suspend fun clearMovies()

}