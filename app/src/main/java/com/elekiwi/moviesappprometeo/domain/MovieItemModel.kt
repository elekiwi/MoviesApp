package com.elekiwi.moviesappprometeo.domain

import java.io.Serializable

data class MovieItemModel(
    var title: String = "",
    var description: String = "",
    var poster: String = "",
    var time: String = "",
    var trailer: String = "",
    var imdb: String = "",
    var year: String = "",
    var price: Double = 0.0,
    var genre: ArrayList<String> = arrayListOf(),
    var casts: ArrayList<CastModel> = arrayListOf(),
    var seen: Boolean = false,
    var toSee: Boolean = false
): Serializable
