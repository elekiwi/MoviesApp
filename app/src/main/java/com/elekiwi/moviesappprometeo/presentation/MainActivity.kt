package com.elekiwi.moviesappprometeo.presentation

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
import com.elekiwi.moviesappprometeo.BottomNavigationBar
import com.elekiwi.moviesappprometeo.MovieItem
import com.elekiwi.moviesappprometeo.R
import com.elekiwi.moviesappprometeo.SearchBar
import com.elekiwi.moviesappprometeo.domain.MovieItemModel
import com.elekiwi.moviesappprometeo.viewModels.MoviesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(onItemClick = {

            })
        }
    }
}

@Preview
@Composable
fun MainScreen(onItemClick: (MovieItemModel) -> Unit = {}) {
    Scaffold(
        bottomBar = { BottomNavigationBar() },
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
                    onClick = {},
                    Modifier
                        .padding(2.dp)
                        .size(58.dp),
                    contentColor = Color.White,
                    containerColor = colorResource(R.color.black3),
                    shape = CircleShape
                ) {
                    Icon(
                        painter = painterResource(R.drawable.float_icon),
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
        }

        MainContent(onItemClick)

    }
}

@Composable
fun MainContent(onItemClick: (MovieItemModel) -> Unit) {

    val viewModel = MoviesViewModel()
    val movies = remember { mutableStateListOf<MovieItemModel>() }

    var showMoviesLoad by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadMovies().observeForever {
            movies.clear()
            movies.addAll(it)
            showMoviesLoad = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 60.dp, bottom = 100.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Wanna add some movies?",
            style = TextStyle(color = Color.White, fontSize = 25.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )

        SearchBar(hint = "Search movies...")

        SectionTitle("Movies")

        if (showMoviesLoad) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(movies) { item ->
                    MovieItem(item, onItemClick)
                }
            }
        }


    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = TextStyle(color = Color(0xffffc107), fontSize = 18.sp),
        modifier = Modifier
            .padding(start = 16.dp, top = 32.dp, bottom = 8.dp)
            .fillMaxWidth(),
        fontWeight = FontWeight.Bold
    )
}
