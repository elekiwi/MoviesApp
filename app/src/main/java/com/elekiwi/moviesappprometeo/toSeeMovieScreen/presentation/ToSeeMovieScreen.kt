package com.elekiwi.moviesappprometeo.toSeeMovieScreen.presentation

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.elekiwi.moviesappprometeo.R
import com.elekiwi.moviesappprometeo.core.domain.models.Movie
import com.elekiwi.moviesappprometeo.core.presentation.Screen
import com.elekiwi.moviesappprometeo.core.presentation.components.BottomNavigationBar
import com.elekiwi.moviesappprometeo.moviesList.presentation.HomeContent
import com.elekiwi.moviesappprometeo.moviesList.presentation.SectionTitle
import com.elekiwi.moviesappprometeo.toSeeMovieScreen.presentation.components.MovieItemToSee

@Composable
fun ToSeeScreen(navController: NavController, onItemClick: (Movie) -> Unit = {}) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colorResource(R.color.pink),
                                colorResource(R.color.green)
                            )
                        ),
                        shape = CircleShape
                    )
                    .padding(3.dp)
            ) {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.AddMovie(-1)) },
                    Modifier
                        .padding(2.dp)
                        .size(58.dp),
                    contentColor = Color.White,
                    containerColor = colorResource(R.color.black3),
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = colorResource(R.color.blackBackground)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(color = colorResource(R.color.blackBackground))
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = null, contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            ToSeeMovieContent(onItemClick)
        }
    }
}

@Composable
fun ToSeeMovieContent(
    onItemClick: (Movie) -> Unit,
    viewModel: ToSeeMovieViewModel = hiltViewModel()
) {
    val state = viewModel.movieListState.collectAsState()

    LaunchedEffect(true) {
        viewModel.getMovieList()
    }

    if (state.value.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = colorResource(R.color.pink))
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, bottom = 100.dp)
                .padding(horizontal = 16.dp)
        ) {


            SectionTitle("Movies To See")

            if (state.value.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(state.value.movieList.size) { item ->
                        val item = state.value.movieList[item]
                        MovieItemToSee(item, onItemClick)
                    }
                }
            }
        }
    }
}
