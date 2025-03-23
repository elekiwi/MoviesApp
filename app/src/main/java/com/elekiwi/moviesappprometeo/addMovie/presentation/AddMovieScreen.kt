package com.elekiwi.moviesappprometeo.addMovie.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elekiwi.moviesappprometeo.R
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
                text = "Add a movie",
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
                onValueChange = {
                    addMovieViewModel.onAction(AddMovieAction.UpdateDescription(it))
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            GradientTextField(
                hint = "Your comment here!",
                modifier = Modifier.fillMaxWidth(),
                text = if (addMovieState.comment == "null") "" else addMovieState.comment,
                onValueChange = {
                    addMovieViewModel.onAction(AddMovieAction.UpdateComment(it))
                },
            )
            Spacer(Modifier.height(64.dp))

            GradientButton(
                text = "Add",
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

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(
            width = 4.dp, brush = Brush.linearGradient(
                colors = listOf(colorResource(R.color.pink), colorResource(R.color.green))
            )
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun GradientTextField(
    hint: String,
    text: String,
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Box(
        modifier = modifier
            .height(60.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(colorResource(R.color.pink), colorResource(R.color.green))
                ),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(4.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { onValueChange(it) },
            placeholder = {
                Text(
                    text = hint,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White,
                textAlign = TextAlign.Center
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White
            ),
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.black1),
                    shape = RoundedCornerShape(50.dp)
                )
                .align(Alignment.Center)
        )
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