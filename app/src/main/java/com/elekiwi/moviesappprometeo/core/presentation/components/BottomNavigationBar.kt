package com.elekiwi.moviesappprometeo.core.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elekiwi.moviesappprometeo.R
import com.elekiwi.moviesappprometeo.core.presentation.Screen

@Composable
fun BottomNavigationBar(navController: NavController) {
    val bottomMenuItemsList = prepareButtomMenu()
    val contextForToast = LocalContext.current.applicationContext
    var selecttedItem by remember {
        mutableStateOf("Home")
    }

    BottomAppBar(
        cutoutShape = CircleShape,
        contentColor = colorResource(R.color.white),
        backgroundColor = colorResource(R.color.black3),
        elevation = 3.dp
    ) {
        bottomMenuItemsList.forEachIndexed { index, bottomMenuItem ->
            if (index == 2) {
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {},
                    enabled = false
                )
            }
            BottomNavigationItem(
                selected = selecttedItem == bottomMenuItem.label,
                onClick = {
                    selecttedItem = bottomMenuItem.label
                    when (selecttedItem) {
                        "Home" -> {
                            navController.navigate(Screen.MovieList)
                        }
                        "To See" -> {
                            navController.navigate(Screen.ToSeeMovie)
                        }
                        "Seen" -> {
                            navController.navigate(Screen.SeenMovie)
                        }
                        "For price" -> {}
                    }
                },
                icon = {
                    Icon(
                        painter = bottomMenuItem.icon,
                        contentDescription = bottomMenuItem.label,
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                    )
                }, label = {
                    Text(
                        text = bottomMenuItem.label,
                        modifier = Modifier.padding(top = 14.dp)
                    )
                },
                alwaysShowLabel = true,
                enabled = true

            )
        }
    }
}

data class BottomMenuItem(
    val label: String,
    val icon: Painter
)

@Composable
fun prepareButtomMenu(): List<BottomMenuItem> {
    return listOf(
        BottomMenuItem(
            label = "Home",
            icon = painterResource(id = R.drawable.btn_1)
        ),
        BottomMenuItem(
            label = "To See",
            icon = painterResource(id = R.drawable.btn_2)
        ),
        BottomMenuItem(
            label = "Seen",
            icon = painterResource(id = R.drawable.btn_4)
        ),
        BottomMenuItem(
            label = "For price",
            icon = painterResource(id = R.drawable.btn_3)
        )
    )
}