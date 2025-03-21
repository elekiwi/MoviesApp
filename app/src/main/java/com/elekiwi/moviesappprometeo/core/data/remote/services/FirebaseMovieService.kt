package com.elekiwi.moviesappprometeo.core.data.remote.services

import com.elekiwi.moviesappprometeo.core.data.remote.MovieItemModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseMovieService @Inject constructor(
    firebaseDatabase: FirebaseDatabase
) {
    val movieRef = firebaseDatabase.getReference("Items")

    suspend fun fetchMovies(): List<MovieItemModel> {
        return try {
            val snapshot = movieRef.get().await()
            snapshot.children.mapNotNull { it.getValue(MovieItemModel::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun addMovie(movie: MovieItemModel) {
        val movieId = generateIntegerId()
        movieRef.child(movieId.toString()).setValue(movie)
    }

    fun generateIntegerId(): Int {
        return (System.currentTimeMillis() / 1000).toInt()
    }
}