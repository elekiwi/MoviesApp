package com.elekiwi.moviesappprometeo.toSeeMovie.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.elekiwi.moviesappprometeo.core.domain.models.Movie

@Composable
fun MovieItemToSee(movie: Movie, onItemClick: (Movie) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onItemClick(movie) }
            .background(color = Color(android.graphics.Color.parseColor("#2f2f39")))
    ) {
        AsyncImage(
            model = movie.poster,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .background(Color.Gray)
                .align(Alignment.Top)
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = movie.title,
                modifier = Modifier.padding(4.dp),
                style = TextStyle(color = Color.White, fontSize = 16.sp),
                maxLines = 1
            )

            Spacer(Modifier.height(8.dp))

            Text(text = movie.description.toString(),
                style = TextStyle(color = Color.White, fontSize = 12.sp))

            Spacer(Modifier.height(8.dp))

            Text(
                text = if (movie.comments.toString() == "null") "" else movie.comments.toString(),
                style = TextStyle(color = Color.White, fontSize = 12.sp)
            )

        }

    }
}