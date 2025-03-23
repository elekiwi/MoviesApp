package com.elekiwi.moviesappprometeo.core.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.elekiwi.moviesappprometeo.detailMovie.presentation.DetailScreen
import com.elekiwi.moviesappprometeo.moviesList.presentation.HomeScreen
import com.elekiwi.moviesappprometeo.addMovie.presentation.AddMovieScreen
import com.elekiwi.moviesappprometeo.core.presentation.ui.theme.MoviesAppPrometeoTheme
import com.elekiwi.moviesappprometeo.toSeeMovieScreen.presentation.ToSeeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppPrometeoTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.MovieList
    ) {

        composable<Screen.MovieList> {
            HomeScreen(
                navController,
                onItemClick = { movie ->
                    navController.navigate(Screen.DetailMovie(movieId = movie.id))
                }
            )
        }

        composable<Screen.AddMovie> { backStackEntry ->
            val addMovieScreen: Screen.AddMovie = backStackEntry.toRoute()
            AddMovieScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveMovie = {
                    navController.popBackStack()
                },
                movieId = addMovieScreen.movieId
            )
        }

        composable<Screen.DetailMovie> { backStackEntry ->
            val movieDetailScreen: Screen.DetailMovie = backStackEntry.toRoute()

            DetailScreen(
                id = movieDetailScreen.movieId,
                onBackClick = {
                    navController.popBackStack()
                },
                onEditClick = {
                    Log.e("LeoDebug3", "Navigation: $it", )
                    navController.navigate(Screen.AddMovie(it))
                }
            )
        }

        composable<Screen.ToSeeMovie> {
            ToSeeScreen(
                navController = navController,
                onItemClick = { movie ->
                    navController.navigate(Screen.DetailMovie(movieId = movie.id))
                }
            )
        }
    }
}

