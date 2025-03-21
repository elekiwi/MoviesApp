package com.elekiwi.moviesappprometeo.core.data.mappers

import com.elekiwi.moviesappprometeo.core.data.local.MovieEntity
import com.elekiwi.moviesappprometeo.core.data.remote.MovieItemModel
import com.elekiwi.moviesappprometeo.core.domain.models.Movie


fun MovieItemModel.toMovieEntity(): MovieEntity {
    return MovieEntity(
        title = this.Title,
        description = this.Description,
        poster = this.Poster,
        time = this.Time,
        trailer = this.Trailer,
        imdb = this.Imdb,
        year = this.Year,
        price = this.price,
        isSeen = this.seen,
        toSee = this.toSee,
        comments = null,
        rating = 0.0
    )
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        title = this.title,
        description = this.description,
        poster = this.poster,
        time = this.time,
        trailer = this.trailer,
        imdb = this.imdb,
        year = this.year,
        price = this.price,
        isSeen = this.isSeen,
        toSee = this.toSee,
        comments = this.comments,
        rating = this.rating
    )
}

fun Movie.ToMovieEntity(): MovieEntity {
    return MovieEntity(
        title = this.title,
        description = this.description,
        poster = this.poster,
        time = this.time,
        trailer = this.trailer,
        imdb = this.imdb,
        year = this.year,
        price = this.price,
        isSeen = this.isSeen,
        toSee = this.toSee,
        comments = this.comments,
        rating = this.rating
    )
}

fun Movie.ToMovieItemModel(): MovieItemModel {
    return MovieItemModel(
        Title = this.title,
        Description = this.description.toString(),
        Poster = this.poster.toString(),
        Time = this.time.toString(),
        Trailer = this.trailer.toString(),
        Imdb = this.imdb,
        Year = this.year,
        price = this.price,
        seen = this.isSeen,
        toSee = this.toSee,
        //comments = null,
        //rating = 0.0
    )
}