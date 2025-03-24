package com.elekiwi.moviesappprometeo.addMovie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elekiwi.moviesappprometeo.addMovie.domain.UpsertMovie
import com.elekiwi.moviesappprometeo.core.domain.models.Movie
import com.elekiwi.moviesappprometeo.core.domain.repositories.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddMovieViewModel @Inject constructor(
    private val repository: MovieListRepository,
    private val upsertMovie: UpsertMovie
) : ViewModel() {
    private var _addMovieState = MutableStateFlow(AddMovieState())
    val addMovieState = _addMovieState.asStateFlow()

    private val _movieSavedChannel = Channel<Boolean>()
    val movieSavedFlow = _movieSavedChannel.receiveAsFlow()

    fun onAction(action: AddMovieAction) {
        when (action) {
            is AddMovieAction.UpdateTitle -> {
                _addMovieState.update {
                    it.copy(title = action.newTitle)
                }
            }

            is AddMovieAction.UpdateDescription -> {
                _addMovieState.update {
                    it.copy(description = action.newDescription)
                }
            }

            AddMovieAction.SaveMovie -> {
                viewModelScope.launch {
                    val isSaved = updateMovie()
                    _movieSavedChannel.send(isSaved)
                }
            }

            is AddMovieAction.LoadMovie -> {
                loadMovie(action.id)
            }

            is AddMovieAction.UpdateComment -> {
                _addMovieState.update {
                    it.copy(comment = action.newComment)
                }
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
                        _addMovieState.update {
                            it.copy(
                                movieId = movie.id,
                                title = movie.title,
                                description = movie.description ?: "",
                                comment = movie.comments ?: "",
                                movie = movie
                            )
                        }
                    }
                }
            }
        }
    }

    private suspend fun updateMovie(): Boolean {

        val currentMovie = _addMovieState.value.movie

        if (currentMovie == null) {
            val updatedMovie = Movie(
                _addMovieState.value.title,
                _addMovieState.value.description,
                "",
                "",
                "",
                0.0,
                0,
                0.0,
                false,
                false,
                _addMovieState.value.comment,
                0,
                id = -1
            )
            return withContext(Dispatchers.IO) {
                upsertMovie.invoke(updatedMovie)
            }
        } else {
            val updatedMovie = currentMovie.copy(
                title = _addMovieState.value.title,
                comments = _addMovieState.value.comment,
                description = _addMovieState.value.description
            )
            return withContext(Dispatchers.IO) {
                upsertMovie.invoke(updatedMovie)
            }
        }
    }
}

