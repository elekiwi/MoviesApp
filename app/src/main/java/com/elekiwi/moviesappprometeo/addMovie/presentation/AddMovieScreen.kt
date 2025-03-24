package com.elekiwi.moviesappprometeo.addMovie.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elekiwi.moviesappprometeo.R
import com.elekiwi.moviesappprometeo.addMovie.presentation.components.GradientButton
import com.elekiwi.moviesappprometeo.addMovie.presentation.components.GradientTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddMovieScreen(
    onBackClick: () -> Unit = {},
    onSaveMovie: () -> Unit = {},
    movieId: Int,
    addMovieViewModel: AddMovieViewModel = hiltViewModel()
) {

    val addMovieState by addMovieViewModel.addMovieState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(movieId) {
        addMovieViewModel.onAction(AddMovieAction.LoadMovie(movieId))
    }


    LaunchedEffect(true) {

        addMovieViewModel.movieSavedFlow.collectLatest { saved ->
            if (saved) {
                onSaveMovie()
            } else {
                Toast.makeText(
                    context,
                    "Invalid Info",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.blackBackground))
    ) {

        Image(
            painter = painterResource(R.drawable.bg1),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 16.dp)
                    .clickable { onBackClick() }
            )

            Spacer(Modifier.height(64.dp))


            Spacer(Modifier.height(16.dp))

            Text(
                text = if (movieId == -1) "Add a movie" else "Edit movie",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(64.dp))

            GradientTextField(
                hint = "Title",
                text = addMovieState.title,
                onValueChange = {
                    addMovieViewModel.onAction(AddMovieAction.UpdateTitle(it))
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))

            GradientTextField(
                hint = "Description",
                text = addMovieState.description,
                maxLines = 4,
                onValueChange = {
                    addMovieViewModel.onAction(AddMovieAction.UpdateDescription(it))
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            GradientTextField(
                hint = "Your comment here!",
                modifier = Modifier.fillMaxWidth(),
                maxLines = 4,
                text = if (addMovieState.comment == "null") "" else addMovieState.comment,
                onValueChange = {
                    addMovieViewModel.onAction(AddMovieAction.UpdateComment(it))
                },
            )
            Spacer(Modifier.height(64.dp))

            GradientButton(
                text = "Save",
                onClick = {
                    addMovieViewModel.onAction(
                        AddMovieAction.SaveMovie
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
        }
    }
}





@Preview
@Composable
fun AddMovieScreenPreview() {
    AddMovieScreen(
        onSaveMovie = {},
        movieId = -1
    )
}