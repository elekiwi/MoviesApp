package com.elekiwi.moviesappprometeo.detailMovie.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.elekiwi.moviesappprometeo.R
import com.elekiwi.moviesappprometeo.detailMovie.presentation.components.RatingBar

@Composable
fun DetailScreen(
    id: Int,
    onBackClick: () -> Unit,
    onEditClick: (Int) -> Unit,
    viewModel: DetailMovieViewModel = hiltViewModel()
) {

    val movieState by viewModel.detailMovieState.collectAsState()
    val ratingState = remember { mutableIntStateOf(movieState.rating) }

    LaunchedEffect(movieState.movie?.rating) {
        movieState.movie?.let {
            ratingState.intValue = it.rating
        }
    }
    LaunchedEffect(id) {
        viewModel.onAction(DetailMovieActions.LoadMovie(id))
    }

    val scrollState = rememberScrollState()
    val isLoading = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.blackBackground))
            .padding(16.dp)
    ) {
        if (isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Box(
                    modifier = Modifier.height(400.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.back),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp, top = 48.dp)
                            .clickable { onBackClick() }
                    )
                    Icon(
                        imageVector = when (movieState.movie?.toSee) {
                            true -> Icons.Filled.Favorite
                            false -> Icons.Filled.FavoriteBorder
                            else -> Icons.Filled.FavoriteBorder
                        },
                        tint = Color.White,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp, top = 48.dp, end = 16.dp)
                            .align(Alignment.TopEnd)
                            .clickable {
                                viewModel.onAction(
                                    DetailMovieActions.UpdateToSee(!movieState.isToSee)
                                )
                            }
                    )
                    AsyncImage(
                        model = movieState.movie?.poster,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        alpha = 0.1f
                    )

                    AsyncImage(
                        model = movieState.movie?.poster,
                        contentDescription = null,
                        modifier = Modifier
                            .size(210.dp, 300.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .align(Alignment.BottomCenter),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        colorResource(R.color.black2),
                                        colorResource(R.color.black1)
                                    ),
                                    start = Offset(0f, 0f),
                                    end = Offset(0f, Float.POSITIVE_INFINITY)
                                )
                            )
                    )

                    Text(
                        text = movieState.movie?.title ?: "Loading...",
                        style = TextStyle(color = Color.White, fontSize = 27.sp),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(end = 16.dp, top = 48.dp)
                    )

                }

                Spacer(Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.star),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                        )
                        Text(
                            text = "Imdb: ${movieState.movie?.imdb}",
                            color = Color.White
                        )

                        Icon(
                            painter = painterResource(R.drawable.time),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                        )
                        Text(
                            text = "Runtime: ${movieState.movie?.time}",
                            color = Color.White
                        )

                        Icon(
                            painter = painterResource(R.drawable.cal),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                        )
                        Text(
                            text = "Release: ${movieState.movie?.year}",
                            color = Color.White
                        )
                    }

                    Spacer(Modifier.height(24.dp))

                    Text(
                        text = "Summary",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    )
                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = movieState.movie?.description.toString(),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    )

                    Spacer(Modifier.height(24.dp))

                    Text(
                        text = "Your rate & comment: ",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    )
                    Spacer(Modifier.height(8.dp))

                    RatingBar(
                        ratingState = ratingState,
                        ratingIconPainter = painterResource(id = R.drawable.star),
                        size = 48.dp,
                        selectedColor = colorResource(R.color.green),
                        onRatingChanged = { newRating ->
                            viewModel.onAction(
                                DetailMovieActions.UpdateRating(newRating)
                            )
                        }
                    )
                    Spacer(Modifier.height(16.dp))

                    if (movieState.movie?.comments != "null") {
                        Text(
                            text = movieState.movie?.comments ?: "No comments yet",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        )
                    }


                }
            }
        }

        FloatingActionButton(
            onClick = {
                onEditClick(movieState.movieId)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .border(
                    4.dp, brush = Brush.linearGradient(
                        colors = listOf(
                            colorResource(R.color.pink),
                            colorResource(R.color.green)
                        )
                    ), RoundedCornerShape(16.dp)
                )
                .padding(0.dp),
            containerColor = colorResource(R.color.black1)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit movie",
                tint = Color.White
            )
        }
    }
}