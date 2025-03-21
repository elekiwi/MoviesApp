package com.elekiwi.moviesappprometeo.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.elekiwi.moviesappprometeo.core.domain.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Upsert
    fun upsertMovie(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int): MovieEntity

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieEntity>

    @Query("DELETE FROM movies")
    suspend fun clearMovies()
}