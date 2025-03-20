package com.elekiwi.moviesappprometeo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.elekiwi.moviesappprometeo.core.data.remote.MovieItemModel

@Composable
fun MovieItem(movie: MovieItemModel, onItemClick: (MovieItemModel) -> Unit) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(120.dp)
            .clickable { onItemClick(movie) }
            .background(color = Color(android.graphics.Color.parseColor("#2f2f39")))
    ) {
        AsyncImage(
            model = movie.Poster,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .background(Color.Gray)
        )

        Spacer(Modifier.height(8.dp))

        Text(text = movie.Title,
            modifier = Modifier.padding(4.dp),
            style = TextStyle(color = Color.White, fontSize = 11.sp),
            maxLines = 1
        )

        Spacer(Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 4.dp, bottom = 4.dp)
        ) {

            Icon(imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color(0xffffc107)
            )

            Spacer(Modifier.width(4.dp))
            Text(text = movie.Imdb.toString(),
                style = TextStyle(color = Color.White, fontSize = 12.sp))

        }
    }
}