package com.elekiwi.moviesappprometeo.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elekiwi.moviesappprometeo.moviesList.presentation.HomeScreen
import com.elekiwi.moviesappprometeo.presentation.AddMovieScreen
import com.elekiwi.moviesappprometeo.presentation.ToSeeMovieScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*MainScreen(onItemClick = { item ->
                val intent = Intent(this, DetailMovieActivity::class.java)
                intent.putExtra("object", item)
                startActivity(intent)
            })*/
            Navigation()
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
                    navController.navigate(Screen.DetailMovie(1))
                }
            )
        }

        composable<Screen.AddMovie> {
            AddMovieScreen(navController, -1)
        }

        composable<Screen.DetailMovie> {
            /*DetailScreen(
                movie = ,
                onBackClick = {
                    navController.popBackStack()
                },
                onFavClick = {

                }
            )*/
        }

        composable<Screen.ToSeeMovie> {
            ToSeeMovieScreen()
        }
    }
}

