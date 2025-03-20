package com.elekiwi.moviesappprometeo.domain

import java.io.Serializable

data class MovieItemModel(
    var Title: String = "",
    var Description: String = "",
    var Poster: String = "",
    var Time: String = "",
    var Trailer: String = "",
    var Imdb: Double = 0.0,
    var Year: Long = 0,
    var price: Double = 0.0,
    var Genre: ArrayList<String> = arrayListOf(),
    var Casts: ArrayList<CastModel> = arrayListOf(),
    var seen: Boolean = false,
    var toSee: Boolean = false
): Serializable
