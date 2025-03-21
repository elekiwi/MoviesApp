package com.elekiwi.moviesappprometeo.core.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.elekiwi.moviesappprometeo.core.presentation.components.BottomNavigationBar
import com.elekiwi.moviesappprometeo.MovieItem
import com.elekiwi.moviesappprometeo.R
import com.elekiwi.moviesappprometeo.core.presentation.components.SearchBar
import com.elekiwi.moviesappprometeo.core.data.remote.MovieItemModel
import com.elekiwi.moviesappprometeo.presentation.AddMovieScreen
import com.elekiwi.moviesappprometeo.presentation.DetailMovieActivity
import com.elekiwi.moviesappprometeo.presentation.DetailScreen
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

