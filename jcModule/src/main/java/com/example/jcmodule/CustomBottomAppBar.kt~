package com.example.jcmodule

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun BounceBottomBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    val items = listOf("Home", "Search", "Favorites", "Profile")

    BottomAppBar {
        items.forEachIndexed { index, item ->
            val bounce by animateFloatAsState(
                targetValue = if (selectedIndex == index) 1.2f else 1f,
                animationSpec = spring(
                    dampingRatio = 0.4f,
                    stiffness = 200f
                )
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = when(index) {
                            0 -> Icons.Default.Home
                            1 -> Icons.Default.Search
                            2 -> Icons.Default.Favorite
                            else -> Icons.Default.Person
                        },
                        contentDescription = item,
                        modifier = Modifier.scale(bounce)
                    )
                },
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) }
            )
        }
    }
}

@Composable
fun ExpandableLabelBottomBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    val items = listOf("Home", "Search", "Notifications", "Menu")

    BottomAppBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = when(index) {
                            0 -> Icons.Default.Home
                            1 -> Icons.Default.Search
                            2 -> Icons.Default.LocationOn
                            else -> Icons.Default.Delete
                        },
                        contentDescription = item
                    )
                },
                label = {
                    AnimatedVisibility(
                        visible = selectedIndex == index,
                        enter = expandHorizontally(),
                        exit = shrinkHorizontally()
                    ) {
                        Text(text = item)
                    }
                },
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) }
            )
        }
    }
}

@Composable
fun ShakeBottomBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    val items = listOf("Home", "Search", "Cart", "Profile")
    val shakeAmplitude = 5f

    BottomAppBar {
        items.forEachIndexed { index, item ->
            val shake by animateFloatAsState(
                targetValue = if (selectedIndex == index) shakeAmplitude else 0f,
                animationSpec = keyframes {
                    durationMillis = 500
                    0f at 0
                    -shakeAmplitude at 125
                    shakeAmplitude at 250
                    -shakeAmplitude/2 at 375
                    0f at 500
                }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = when(index) {
                            0 -> Icons.Default.Home
                            1 -> Icons.Default.Search
                            2 -> Icons.Default.ShoppingCart
                            else -> Icons.Default.Person
                        },
                        contentDescription = item,
                        modifier = Modifier
                            .graphicsLayer {
                                translationX = shake
                            }
                    )
                },
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) }
            )
        }
    }
}




@Composable
fun ExpandOnClickBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf("Home", "Search", "Add", "Profile")

    BottomAppBar {
        items.forEachIndexed { index, item ->
            val isSelected = selectedIndex == index
            val width by animateDpAsState(
                targetValue = if (isSelected) 120.dp else 60.dp,
                animationSpec = tween(300)
            )

            NavigationBarItem(
                icon = {
                    AnimatedContent(
                        targetState = isSelected,
                        transitionSpec = {
                            fadeIn().togetherWith(fadeOut())
                        }
                    ) { expanded ->
                        if (expanded) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = when (index) {
                                        0 -> Icons.Default.Home
                                        1 -> Icons.Default.Search
                                        2 -> Icons.Default.Add
                                        else -> Icons.Default.Person
                                    },
                                    contentDescription = null
                                )
                                Text(text = item)
                            }
                        } else {
                            Icon(
                                imageVector = when (index) {
                                    0 -> Icons.Default.Home
                                    1 -> Icons.Default.Search
                                    2 -> Icons.Default.Add
                                    else -> Icons.Default.Person
                                },
                                contentDescription = item
                            )
                        }
                    }
                },
                selected = isSelected,
                onClick = { onItemSelected(index) },
                modifier = Modifier.width(width)
            )
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScaleAnimatedBottomBarPreview() {
    var selectedIndex by remember { mutableStateOf(0) }

    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly) {

        BounceBottomBar(
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it }
        )
        ExpandableLabelBottomBar(
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it }
        )
        ShakeBottomBar(
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it }
        )
        ExpandOnClickBottomBar(
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it }
        )
    }
}

