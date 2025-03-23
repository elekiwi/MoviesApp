package com.elekiwi.moviesappprometeo.detailMovie.presentation.components

import android.view.MotionEvent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.elekiwi.moviesappprometeo.R

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    ratingState: MutableState<Int> = remember { mutableIntStateOf(0) },
    ratingIconPainter: Painter = painterResource(id = R.drawable.star),
    selectedColor: Color = Color(0xFFFFD700),
    unselectedColor: Color = Color(0xFFA2ADB1),
    onRatingChanged: (Int) -> Unit = {}
) {
    Row(modifier = modifier.wrapContentSize()) {
        for (value in 1..5) {
            StarIcon(
                size = size,
                painter = ratingIconPainter,
                ratingValue = value,
                ratingState = ratingState,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                onRatingChanged = onRatingChanged
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StarIcon(
    size: Dp,
    ratingState: MutableState<Int>,
    painter: Painter,
    ratingValue: Int,
    selectedColor: Color,
    unselectedColor: Color,
    onRatingChanged: (Int) -> Unit
) {
    val tint by animateColorAsState(
        targetValue = if (ratingValue <= ratingState.value) selectedColor else unselectedColor,
        label = ""
    )

    Icon(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .padding(2.dp)
            .size(size)
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        ratingState.value = ratingValue
                        onRatingChanged(ratingValue)
                    }
                }
                true
            },
        tint = tint
    )
}

@Preview
@Composable
fun PreviewRatingBar() {
    val ratingState = remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RatingBar(ratingState = ratingState)
    }
}