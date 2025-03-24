package com.elekiwi.moviesappprometeo.detailMovie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elekiwi.moviesappprometeo.core.domain.repositories.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val repository: MovieListRepository
): ViewModel() {

    private var _detailMovieState = MutableStateFlow(DetailMovieState())
    val detailMovieState = _detailMovieState.asStateFlow()

    fun onAction(action: DetailMovieActions) {
        when (action) {
            is DetailMovieActions.LoadMovie -> {
                loadMovie(action.id)
            }
            is DetailMovieActions.UpdateComment -> {
                _detailMovieState.update {
                    it.copy(comment = action.comment,
                        movie = it.movie?.copy(comments = action.comment)
                    )
                }
                updateMovie()
            }
            is DetailMovieActions.UpdateRating -> {
                _detailMovieState.update {
                    it.copy(rating = action.rating,
                        movie = it.movie?.copy(rating = action.rating)
                    )
                }
                updateMovie()

            }
            is  DetailMovieActions.UpdateToSee -> {
                _detailMovieState.update {
                    it.copy(isToSee = action.isToSee,
                        movie = it.movie?.copy(toSee = action.isToSee))
                }
                updateMovie()
            }
        }
    }

    private fun loadMovie(id: Int) {

        if (id == -1) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val movieFlow = repository.getMovieById(id)

            movieFlow.collectLatest { result ->
                result.data.let { movie ->
                    movie?.let {
                        _detailMovieState.update {
                            it.copy(movieId = movie.id, isToSee = movie.toSee, rating = movie.rating, comment = movie.comments.toString(), movie = movie)
                        }
                    }
                }
            }
        }
    }

    fun updateMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentMovie = _detailMovieState.value.movie ?: return@launch

            val updatedMovie = currentMovie.copy(
                rating = _detailMovieState.value.rating,
                comments = _detailMovieState.value.comment,
                toSee = _detailMovieState.value.isToSee
            )

            repository.upsertMovie(updatedMovie)
        }
    }

}