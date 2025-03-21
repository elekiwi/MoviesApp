package com.elekiwi.moviesappprometeo.core.data.repositories

import com.elekiwi.moviesappprometeo.core.data.local.MovieDao
import com.elekiwi.moviesappprometeo.core.data.local.MovieDatabase
import com.elekiwi.moviesappprometeo.core.data.remote.services.FirebaseMovieService
import com.elekiwi.moviesappprometeo.core.domain.models.Movie
import com.elekiwi.moviesappprometeo.core.domain.repositories.MovieListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val firebaseService: FirebaseMovieService
): MovieListRepository {
    override suspend fun upsertMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override suspend fun insertAllMovies(movies: List<Movie>) {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieById(id: Int): Movie? {
        TODO("Not yet implemented")
    }

    override fun getAllMovies(): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun clearMovies() {
        TODO("Not yet implemented")
    }

}