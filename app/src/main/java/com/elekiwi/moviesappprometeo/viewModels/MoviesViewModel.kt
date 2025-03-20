package com.elekiwi.moviesappprometeo.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elekiwi.moviesappprometeo.domain.MovieItemModel
import com.elekiwi.moviesappprometeo.repositories.MoviesRepository

class MoviesViewModel: ViewModel() {
    private val moviesRepository = MoviesRepository()

    fun loadMovies(): LiveData<MutableList<MovieItemModel>> {
        return moviesRepository.loadMovies()
    }
}