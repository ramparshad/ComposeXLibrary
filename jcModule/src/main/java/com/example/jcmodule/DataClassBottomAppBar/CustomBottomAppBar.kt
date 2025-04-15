package com.example.jcmodule.DataClassBottomAppBar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BounceBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    items: List<BounceBottomBarItem>,
    modifier: Modifier = Modifier,
    bounceScale: Float = 1.2f,
    animationSpec: AnimationSpec<Float> = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessMedium
    ),
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = contentColor.copy(alpha = 0.6f),
    tonalElevation: Dp = 8.dp,
    showLabels: Boolean = true,
    barHeight: Dp = 80.dp
) {
    BottomAppBar(
        modifier = modifier.height(barHeight),
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation
    ) {
        items.forEachIndexed { index, item ->
            val bounce by animateFloatAsState(
                targetValue = if (selectedIndex == index) bounceScale else 1f,
                animationSpec = animationSpec,
                label = "bounce_animation_$index"
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (selectedIndex == index) item.selectedIcon ?: item.icon else item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.scale(bounce),
                        tint = if (selectedIndex == index) selectedColor else unselectedColor
                    )
                },
                label = if (showLabels) {
                    { Text(text = item.label) }
                } else null,
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = selectedColor,
                    unselectedIconColor = unselectedColor,
                    selectedTextColor = selectedColor,
                    unselectedTextColor = unselectedColor,
                    indicatorColor = containerColor
                )
            )
        }
    }
}
fun defaultBounceBottomBarItems(): List<BounceBottomBarItem> = listOf(
    BounceBottomBarItem(Icons.Default.Home, "Home"),
    BounceBottomBarItem(Icons.Default.Search, "Search"),
    BounceBottomBarItem(Icons.Default.Favorite, "Favorites"),
    BounceBottomBarItem(Icons.Default.Person, "Profile")
)

fun defaultBounceBottomBarItemsWithSelectedIcons(): List<BounceBottomBarItem> = listOf(
    BounceBottomBarItem(
        icon = Icons.Default.Home,
        selectedIcon = Icons.Filled.Home,
        label = "Home"
    ),
    BounceBottomBarItem(
        icon = Icons.Default.Search,
        selectedIcon = Icons.Filled.Search,
        label = "Search"
    ),
    BounceBottomBarItem(
        icon = Icons.Default.Favorite,
        selectedIcon = Icons.Filled.Favorite,
        label = "Favorites"
    ),
    BounceBottomBarItem(
        icon = Icons.Default.Person,
        selectedIcon = Icons.Filled.Person,
        label = "Profile"
    )
)


// ---------------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableLabelBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    items: List<ExpandableBottomBarItem>,
    modifier: Modifier = Modifier,
    animationDuration: Int = 300,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = contentColor.copy(alpha = 0.6f),
    tonalElevation: Dp = 8.dp,
    barHeight: Dp = 80.dp
) {
    BottomAppBar(
        modifier = modifier.height(barHeight),
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (selectedIndex == index) item.selectedIcon ?: item.icon else item.icon,
                        contentDescription = item.label,
                        tint = if (selectedIndex == index) selectedColor else unselectedColor
                    )
                },
                label = {
                    AnimatedVisibility(
                        visible = selectedIndex == index,
                        enter = expandHorizontally(animationSpec = tween(animationDuration)),
                        exit = shrinkHorizontally(animationSpec = tween(animationDuration))
                    ) {
                        Text(
                            text = item.label,
                            color = selectedColor
                        )
                    }
                },
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = selectedColor,
                    unselectedIconColor = unselectedColor,
                    selectedTextColor = selectedColor,
                    unselectedTextColor = unselectedColor,
                    indicatorColor = containerColor
                )
            )
        }
    }
}

fun defaultExpandableBottomBarItems(): List<ExpandableBottomBarItem> = listOf(
    ExpandableBottomBarItem(Icons.Default.Home, "Home"),
    ExpandableBottomBarItem(Icons.Default.Search, "Search"),
    ExpandableBottomBarItem(Icons.Default.Notifications, "Notifications"),
    ExpandableBottomBarItem(Icons.Default.Menu, "Menu")
)
fun defaultExpandableBottomBarItemsWithSelectedIcons(): List<ExpandableBottomBarItem> = listOf(
    ExpandableBottomBarItem(
        icon = Icons.Default.Home,
        selectedIcon = Icons.Filled.Home,
        label = "Home"
    ),
    ExpandableBottomBarItem(
        icon = Icons.Default.Search,
        selectedIcon = Icons.Filled.Search,
        label = "Search"
    ),
    ExpandableBottomBarItem(
        icon = Icons.Default.Notifications,
        selectedIcon = Icons.Filled.Notifications,
        label = "Notifications"
    ),
    ExpandableBottomBarItem(
        icon = Icons.Default.Menu,
        selectedIcon = Icons.Filled.Menu,
        label = "Menu"
    )
)


// ---------------------------------------------------------------------

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
            onItemSelected = { selectedIndex = it },
            items = defaultBounceBottomBarItems(),
            barHeight = 80.dp
        )

        ExpandableLabelBottomBar(
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it },
            items = defaultExpandableBottomBarItems(),
            barHeight = 80.dp
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

