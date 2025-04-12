package com.example.jcmodule

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AnimatedSearchAppBar(
    title: String,
    onSearchQueryChanged: (String) -> Unit,
    onMenuClicked: () -> Unit
) {
    var searchActive by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AnimatedVisibility(
                    visible = !searchActive,
                    enter = fadeIn() + expandHorizontally(),
                    exit = fadeOut() + shrinkHorizontally()
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                AnimatedVisibility(
                    visible = searchActive,
                    enter = fadeIn() + expandHorizontally(),
                    exit = fadeOut() + shrinkHorizontally()
                ) {
                    TextField(
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                            onSearchQueryChanged(it)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequester),
                        placeholder = { Text("Search...") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(
                            onSearch = { keyboardController?.hide() }
                        ),
                        singleLine = true
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    if (searchActive) {
                        searchActive = false
                        searchQuery = ""
                        keyboardController?.hide()
                    } else {
                        onMenuClicked()
                    }
                }
            ) {
                Icon(
                    imageVector = if (searchActive) Icons.Default.ArrowBack else Icons.Default.Menu,
                    contentDescription = if (searchActive) "Back" else "Menu",
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        actions = {
            AnimatedVisibility(
                visible = !searchActive,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Row {
                    IconButton(
                        onClick = { searchActive = true },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = searchActive,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(
                    onClick = {
                        searchQuery = ""
                        onSearchQueryChanged("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )

    LaunchedEffect(searchActive) {
        if (searchActive) focusRequester.requestFocus()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun LiquidMotionAppBar(
    scrollState: ScrollState,
    title: String,
    onActionClick: (String) -> Unit
) {
    val scrollThreshold = with(LocalDensity.current) { 150.dp.toPx() }
    val scrollProgress = minOf(1f, abs(scrollState.value) / scrollThreshold)

    // Dynamic properties based on scroll
    val (elevation, height, cornerRadius) = remember(scrollProgress) {
        Triple(
            lerp(0.dp, 12.dp, scrollProgress),
            lerp(96.dp, 72.dp, scrollProgress),
            lerp(0.dp, 24.dp, scrollProgress)
        )
    }

    // Color interpolation
    val startColor = MaterialTheme.colorScheme.primaryContainer
    val endColor = MaterialTheme.colorScheme.surface
    val bgColor by animateColorAsState(
        targetValue = lerp(startColor, endColor, scrollProgress),
        animationSpec = tween(500)
    )

    // Wave effect animation
    val infiniteTransition = rememberInfiniteTransition()
    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .shadow(elevation, shape = RoundedCornerShape(cornerRadius))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    drawContent()
                    // Draw wave effect
                    drawRect(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                startColor.copy(alpha = 0.3f),
                                Color.Transparent
                            ),
                            start = Offset(waveOffset, 0f),
                            end = Offset(waveOffset + 200f, 0f)
                        ),
                        alpha = 0.2f
                    )
                }
                .background(bgColor)
        ) {
            // Title with complex animation
            AnimatedContent(
                targetState = scrollProgress > 0.5f,
                transitionSpec = {
                    fadeIn(animationSpec = tween(300)) +
                            slideInVertically { -it } with
                            fadeOut(animationSpec = tween(300)) +
                            slideOutVertically { it }
                }
            ) { collapsed ->
                Text(
                    text = if (collapsed) "⋮ $title" else title,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 24.dp)
                        .graphicsLayer {
                            alpha = 1f - (scrollProgress * 0.5f)
                            scaleX = lerp(1f, 0.9f, scrollProgress)
                            scaleY = lerp(1f, 0.9f, scrollProgress)
                        },
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.5.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Animated action buttons
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val actions = listOf(
                    "search" to Icons.Default.Search,
                    "favorite" to Icons.Default.Favorite,
                    "share" to Icons.Default.Share
                )

                actions.forEach { (action, icon) ->
                    FloatingActionButton(
                        onClick = { onActionClick(action) },
                        modifier = Modifier
                            .size(if (scrollProgress > 0.7f) 40.dp else 48.dp)
                            .graphicsLayer {
                                rotationZ = lerp(0f, 5f, scrollProgress)
                                alpha = 1f - (scrollProgress * 0.7f)
                            },
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        elevation = FloatingActionButtonDefaults.elevation(
                            defaultElevation = lerp(4.dp, 2.dp, scrollProgress)
                        )
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = action,
                            tint = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                }

                // Morphing menu button
                AnimatedVisibility(
                    visible = scrollProgress > 0.7f,
                    enter = fadeIn() + scaleIn(),
                    exit = fadeOut() + scaleOut()
                ) {
                    FloatingActionButton(
                        onClick = { onActionClick("menu") },
                        modifier = Modifier.size(40.dp),
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menu",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }

            // Bottom border animation
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .height(2.dp)
                    .graphicsLayer {
                        scaleX = lerp(1f, 0.7f, scrollProgress)
                    }
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                Color.Transparent
                            ),
                            startX = 0f,
                            endX = 300f
                        )
                    )
            )
        }
    }
}

// Helper functions
fun lerp(start: Dp, stop: Dp, fraction: Float): Dp {
    return (start + (stop - start) * fraction)
}

fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (start + (stop - start) * fraction)
}


@Preview(showBackground = true, showSystemUi =true)
@Composable
fun JetBlurTopAppBarPreview() {

    AnimatedSearchAppBar(
        title = "Search",
        onSearchQueryChanged = {},
        onMenuClicked = {}
    )
    LiquidMotionAppBar(
        scrollState = rememberScrollState(),
        title = "Liquid Motion",
        onActionClick = {}
    )
}
