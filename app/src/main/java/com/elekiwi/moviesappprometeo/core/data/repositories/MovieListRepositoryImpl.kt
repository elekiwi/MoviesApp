package com.elekiwi.moviesappprometeo.core.data.repositories

import android.util.Log
import com.elekiwi.moviesappprometeo.core.data.local.MovieDao
import com.elekiwi.moviesappprometeo.core.data.local.MovieDatabase
import com.elekiwi.moviesappprometeo.core.data.mappers.ToMovieEntity
import com.elekiwi.moviesappprometeo.core.data.mappers.ToMovieItemModel
import com.elekiwi.moviesappprometeo.core.data.mappers.toMovie
import com.elekiwi.moviesappprometeo.core.data.mappers.toMovieEntity
import com.elekiwi.moviesappprometeo.core.data.remote.services.FirebaseMovieService
import com.elekiwi.moviesappprometeo.core.domain.models.Movie
import com.elekiwi.moviesappprometeo.core.domain.repositories.MovieListRepository
import com.elekiwi.moviesappprometeo.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val firebaseService: FirebaseMovieService
): MovieListRepository {
    override suspend fun upsertMovie(movie: Movie) {
        movieDao.upsertMovie(movie.ToMovieEntity())
    }

    override suspend fun insertAllMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies.map { it.ToMovieEntity() })
        movies.forEach { firebaseService.addMovie(it.ToMovieItemModel()) }
    }

    override suspend fun getMovieById(id: Int): Flow<Resource<Movie>> {
        return flow {

            emit(Resource.Loading(true))

            val movieEntity = movieDao.getMovieById(id)

            if (movieEntity != null) {
                emit(
                    Resource.Success(data = movieEntity.toMovie())
                )

                emit(Resource.Loading(false))
                return@flow
            }

            emit(Resource.Error(message =  "Error no such movie"))
            emit(Resource.Loading(false))

        }
    }

    override fun getAllMovies(): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            var localMovieList = movieDao.getAllMovies()
            if (localMovieList.toList().isEmpty()) {
                val remoteMovies = firebaseService.fetchMovies()
                if (remoteMovies.isNotEmpty()) {
                    movieDao.clearMovies()
                    movieDao.insertMovies(remoteMovies.map { it.toMovieEntity() })
                    localMovieList = movieDao.getAllMovies()
                }
            }

            emit(
                Resource.Success(data = localMovieList.map { movieEntity ->
                    movieEntity.toMovie()
                })
            )
            emit(Resource.Loading(false))
            return@flow

        }
    }


    override suspend fun clearMovies() {
        movieDao.clearMovies()
    }

}