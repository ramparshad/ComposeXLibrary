package com.example.jcmodule

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun RippleWaveButton(text: String, onClick: () -> Unit) {
    var clicked by remember { mutableStateOf(false) }

    Button(
        onClick = {
            clicked = true
            onClick()
        },
        modifier = Modifier
            .padding(8.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            this@Button.AnimatedVisibility(
                visible = clicked,
                enter = fadeIn() + expandHorizontally(),
                exit = fadeOut() + shrinkHorizontally()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.3f),
                                    Color.Transparent
                                )
                            )
                        )
                )
            }
            Text(text)
            LaunchedEffect(clicked) {
                delay(300)
                clicked = false
            }
        }
    }
}

@Composable
fun BouncingButton(text: String, onClick: () -> Unit) {
    var bounce by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (bounce) 0.9f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Button(
        onClick = {
            bounce = true
            onClick()
        },
        modifier = Modifier
            .graphicsLayer { scaleX = scale; scaleY = scale }
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        )
    ) {
        Text(text)
    }

    LaunchedEffect(bounce) {
        if (bounce) {
            delay(300)
            bounce = false
        }
    }
}

@Composable
fun FlipButton(text: String, onClick: () -> Unit) {
    var flipped by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (flipped) 180f else 0f,
        animationSpec = tween(500)
    )

    Button(
        onClick = {
            flipped = !flipped
            onClick()
        },
        modifier = Modifier
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (flipped) MaterialTheme.colorScheme.secondary
            else MaterialTheme.colorScheme.primary,
            contentColor = if (flipped) MaterialTheme.colorScheme.onSecondary
            else MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(if (flipped) "✓ Done" else text)
    }
}

@Composable
fun BouncyTextButton(
    text: String,
    onClick: () -> Unit
) {
    var bounce by remember { mutableStateOf(false) }
    val offsetY by animateDpAsState(
        targetValue = if (bounce) (-4).dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    TextButton(
        onClick = {
            bounce = true
            onClick()
        },
        modifier = Modifier
            .offset(y = offsetY)
    ) {
        Text(text)
    }

    LaunchedEffect(bounce) {
        if (bounce) {
            delay(300)
            bounce = false
        }
    }
}


@Composable
fun BreathingActionButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(700, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onTertiary
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun MorphingFAB(
    icon: ImageVector,
    text: String,
    expanded: Boolean,
    onClick: () -> Unit
) {
    val transition = updateTransition(expanded, label = "morphTransition")
    val width by transition.animateDp(
        transitionSpec = { spring(stiffness = Spring.StiffnessMediumLow) },
        label = "width"
    ) { if (it) 160.dp else 56.dp }
    val cornerRadius by transition.animateDp(
        label = "cornerRadius",
        transitionSpec = { tween(300) }
    ) { if (it) 28.dp else 56.dp }

    Surface(
        onClick = onClick,
        modifier = Modifier
            .width(width)
            .height(56.dp),
        shape = RoundedCornerShape(cornerRadius),
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn() + expandHorizontally(),
                exit = fadeOut() + shrinkHorizontally()
            ) {
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}



@Composable
fun LiquidFillButton(text: String, onClick: () -> Unit) {
    var filled by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (filled) 1f else 0f,
        animationSpec = tween(500)
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                filled = !filled
                onClick()
            }
            .height(48.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primary)
        )
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center),
            color = if (filled) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.primary
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IconTransformButton(onClick: () -> Unit) {
    var clicked by remember { mutableStateOf(false) }

    AnimatedContent(
        targetState = clicked,
        transitionSpec = {
            fadeIn() with fadeOut() using SizeTransform(clip = false)
        }
    ) { isClicked ->
        IconButton(onClick = {
            clicked = !clicked
            onClick()
        }) {
            if (isClicked) {
                Icon(Icons.Default.Check, "Done")
            } else {
                Icon(Icons.Default.Add, "Add")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RippleWaveButtonPreview() {
    Column {
        RippleWaveButton(text = "Click Me", onClick = {})

        BouncingButton(text = "Click Me", onClick = {})

        FlipButton(text = "Click Me", onClick = {})

        BouncyTextButton(text = "Click Me", onClick = {})

        BreathingActionButton(
            icon = Icons.Default.Favorite,
            onClick = {}
        )

        MorphingFAB(
            icon = Icons.Default.Favorite,
            text = "Click Me",
            expanded = true,
            onClick = {}
        )
        LiquidFillButton(text = "Click Me", onClick = {})

        IconTransformButton(onClick = {})



    }
}
