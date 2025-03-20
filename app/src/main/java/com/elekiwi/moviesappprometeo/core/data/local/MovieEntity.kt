package com.elekiwi.moviesappprometeo.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    val title: String,
    val description: String?,
    val poster: String?,
    val time: String?,
    val trailer: String?,
    val imdb: Double,
    val year: Long,
    val price: Double,
    val isSeen: Boolean = false,
    val toSee: Boolean = false,
    val comments: String?,
    val rating: Double,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
