package com.elekiwi.moviesappprometeo.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Upsert
    fun upsertTodo(movieEntity: MovieEntity)

    @Delete
    fun deleteTodo(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int): MovieEntity

    @Query("SELECT * FROM movies")
    fun getAllTodos(): Flow<List<MovieEntity>>

}