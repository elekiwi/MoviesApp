package com.elekiwi.moviesappprometeo.core.domain.models

data class Movie(
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
    val comments: String? = "",
    val rating: Int,
    val id: Int
)
