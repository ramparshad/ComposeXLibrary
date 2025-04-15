package com.example.jcmodule.AnimatedButton

// ScaleButton.kt

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random


@Composable
fun ScaleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Button",
    textColor: Color = Color.White,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    disabledContainerColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    shape: Shape = MaterialTheme.shapes.medium,
    border: BorderStroke? = null,
    icon: @Composable (() -> Unit)? = null,
    iconImage: ImageVector? = null,
    iconPlacement: IconPlacement = IconPlacement.START,
    iconTint: Color = contentColor,
    animationDuration: Int = 200,
    scaleFactor: Float = 0.95f,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    var isClicked by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isClicked) scaleFactor else 1f,
        animationSpec = tween(animationDuration),
        label = "scale_animation",
        finishedListener = { isClicked = false }
    )

    Button(
        onClick = {
            isClicked = true
            onClick()
        },
        modifier = modifier.scale(scale),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            disabledContainerColor = disabledContainerColor,
            contentColor = contentColor,
            disabledContentColor = disabledContentColor
        ),
        elevation = elevation,
        shape = shape,
        border = border,
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        when {
            icon != null -> IconContent(
                text = text,
                icon = icon,
                iconPlacement = iconPlacement,
                textColor = contentColor
            )
            iconImage != null -> IconContent(
                text = text,
                iconImage = iconImage,
                iconPlacement = iconPlacement,
                textColor = contentColor,
                iconTint = iconTint
            )
            else -> Text(text, color = contentColor)
        }
    }
}


@Composable
fun RotateButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Button",
    textColor: Color = Color.White,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    disabledContainerColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    shape: Shape = MaterialTheme.shapes.medium,
    border: BorderStroke? = null,
    icon: @Composable (() -> Unit)? = null,
    iconImage: ImageVector? = null,
    iconPlacement: IconPlacement = IconPlacement.START,
    iconTint: Color = contentColor,
    animationDuration: Int = 500,
    rotationDegrees: Float = 360f,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    var isClicked by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isClicked) rotationDegrees else 0f,
        animationSpec = tween(durationMillis = animationDuration),
        label = "rotate_animation",
        finishedListener = { isClicked = false }
    )

    Button(
        onClick = {
            isClicked = true
            onClick()
        },
        modifier = modifier.rotate(rotation),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            disabledContainerColor = disabledContainerColor,
            contentColor = contentColor,
            disabledContentColor = disabledContentColor
        ),
        elevation = elevation,
        shape = shape,
        border = border,
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        when {
            icon != null -> IconContent(
                text = text,
                icon = icon,
                iconPlacement = iconPlacement,
                textColor = contentColor
            )
            iconImage != null -> IconContent(
                text = text,
                iconImage = iconImage,
                iconPlacement = iconPlacement,
                textColor = contentColor,
                iconTint = iconTint
            )
            else -> Text(text, color = contentColor)
        }
    }
}






@Composable
fun FadeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Button",
    textColor: Color = Color.White,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    disabledContainerColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    shape: Shape = MaterialTheme.shapes.medium,
    border: BorderStroke? = null,
    icon: @Composable (() -> Unit)? = null,
    iconImage: ImageVector? = null,
    iconPlacement: IconPlacement = IconPlacement.START,
    iconTint: Color = contentColor,
    animationDuration: Int = 300,
    fadeToOpacity: Float = 0.5f,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    var isClicked by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (isClicked) fadeToOpacity else 1f,
        animationSpec = tween(animationDuration),
        label = "fade_animation",
        finishedListener = { isClicked = false }
    )

    Button(
        onClick = {
            isClicked = true
            onClick()
        },
        modifier = modifier.alpha(alpha),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            disabledContainerColor = disabledContainerColor,
            contentColor = contentColor,
            disabledContentColor = disabledContentColor
        ),
        elevation = elevation,
        shape = shape,
        border = border,
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        when {
            icon != null -> IconContent(
                text = text,
                icon = icon,
                iconPlacement = iconPlacement,
                textColor = contentColor
            )
            iconImage != null -> IconContent(
                text = text,
                iconImage = iconImage,
                iconPlacement = iconPlacement,
                textColor = contentColor,
                iconTint = iconTint
            )
            else -> Text(text, color = contentColor)
        }
    }
}




@Composable
fun ShakeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Button",
    textColor: Color = Color.White,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    disabledContainerColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    shape: Shape = MaterialTheme.shapes.medium,
    border: BorderStroke? = null,
    icon: @Composable (() -> Unit)? = null,
    iconImage: ImageVector? = null,
    iconPlacement: IconPlacement = IconPlacement.START,
    iconTint: Color = contentColor,
    animationDuration: Int = 500,
    shakeDistance: Dp = 10.dp,
    shakeCount: Int = 3,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    var isClicked by remember { mutableStateOf(false) }
    val shakeOffset by animateDpAsState(
        targetValue = if (isClicked) shakeDistance else 0.dp,
        animationSpec = keyframes {
            durationMillis = animationDuration
            for (i in 0..shakeCount) {
                if (i % 2 == 0) shakeDistance at (i * animationDuration / (shakeCount * 2))
                else (-shakeDistance) at (i * animationDuration / (shakeCount * 2))
            }
            0.dp at durationMillis
        },
        label = "shake_animation",
        finishedListener = { isClicked = false }
    )

    Button(
        onClick = {
            isClicked = true
            onClick()
        },
        modifier = modifier.offset(x = shakeOffset),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            disabledContainerColor = disabledContainerColor,
            contentColor = contentColor,
            disabledContentColor = disabledContentColor
        ),
        elevation = elevation,
        shape = shape,
        border = border,
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        when {
            icon != null -> IconContent(
                text = text,
                icon = icon,
                iconPlacement = iconPlacement,
                textColor = contentColor
            )
            iconImage != null -> IconContent(
                text = text,
                iconImage = iconImage,
                iconPlacement = iconPlacement,
                textColor = contentColor,
                iconTint = iconTint
            )
            else -> Text(text, color = contentColor)
        }
    }
}


@Composable
fun RewardButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Claim Reward",
    coinColor: Color = Color(0xFFFFD700),
    glowColor: Color = Color(0x66FFC107),
    particleColor: Color = Color(0xFFFFEB3B),
    enabled: Boolean = true
) {
    // Animation states
    var isClaiming by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition()

    // 1. Coin flip animation
    val coinRotation by animateFloatAsState(
        targetValue = if (isClaiming) 1440f else 0f, // 4 full rotations
        animationSpec = tween(1000, easing = FastOutSlowInEasing),
        label = "coin_flip",
        finishedListener = { isClaiming = false }
    )

    // 2. Radial glow pulse
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_pulse"
    )

    // 3. Particle explosion
    val particles = remember { List(12) { Random.nextInt(0, 360) } }
    val particleOffset by animateDpAsState(
        targetValue = if (isClaiming) 48.dp else 0.dp,
        animationSpec = spring(dampingRatio = 0.2f),
        label = "particle_burst"
    )

    Box(
        modifier = modifier
            .clickable(enabled = enabled) {
                isClaiming = true
                onClick()
            }
            .background(Color(0xFF4CAF50), RoundedCornerShape(16.dp))
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        // Background glow when active
        if (isClaiming) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                glowColor.copy(alpha = glowAlpha),
                                Color.Transparent
                            )
                        )
                    )
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            // Animated coin
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .graphicsLayer {
                        rotationY = coinRotation
                        cameraDistance = 8f * density
                    }
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                coinColor,
                                coinColor.copy(alpha = 0.8f),
                                coinColor
                            )
                        ),
                        shape = CircleShape
                    )
                    .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Text
            Text(
                text = text,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }

        // Particles
        if (isClaiming) {
            particles.forEach { angle ->
                val radians = Math.toRadians(angle.toDouble())
                Box(
                    modifier = Modifier
                        .offset(
                            x = (particleOffset.value * cos(radians)).dp,
                            y = (particleOffset.value * sin(radians)).dp
                        )
                        .size(6.dp)
                        .background(particleColor, CircleShape)
                )
            }
        }
    }
}



@Composable
fun BlinkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Lightbulb,
    buttonColor: Color = Color.Yellow.copy(alpha = 0.3f),
    glowColor: Color = Color.Yellow,
    size: Dp = 56.dp,
    animationDuration: Int = 800
) {
    var animate by remember { mutableStateOf(false) }
    val glowAlpha by animateFloatAsState(
        targetValue = if (animate) 0.8f else 0.2f,
        animationSpec = keyframes {
            durationMillis = animationDuration
            0.2f at 0
            0.8f at animationDuration / 4
            0.2f at animationDuration / 2
            0.8f at 3 * animationDuration / 4
            0.2f at animationDuration
        },
        finishedListener = { animate = false }
    )

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(glowColor.copy(alpha = glowAlpha))
    ) {
        Box(
            modifier = Modifier
                .size(size - 8.dp)
                .clip(CircleShape)
                .background(buttonColor)
                .clickable {
                    animate = true
                    onClick()
                }
                .align(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = "Light Bulb", tint = Color.White)
        }
    }
}


@Composable
fun DoorLockButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Lock,
    buttonColor: Color = MaterialTheme.colorScheme.secondary,
    lockColor: Color = Color.Green,
    size: Dp = 56.dp,
    animationDuration: Int = 500
) {
    var animate by remember { mutableStateOf(false) }
    val slideOffset by animateFloatAsState(
        targetValue = if (animate) 10f else 0f,
        animationSpec = tween(animationDuration / 2, easing = FastOutLinearInEasing),
        finishedListener = { animate = false }
    )
    val glowAlpha by animateFloatAsState(
        targetValue = if (animate) 0.5f else 0f,
        animationSpec = tween(animationDuration / 2)
    )

    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(12.dp))
            .background(buttonColor.copy(alpha = 0.8f))
            .border(
                2.dp,
                lockColor.copy(alpha = glowAlpha),
                RoundedCornerShape(12.dp)
            )
            .clickable {
                animate = true
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            icon,
            contentDescription = "Door Lock",
            tint = lockColor,
            modifier = Modifier.offset(x = slideOffset.dp)
        )
    }
}


@Composable
fun CoffeeCupButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    buttonColor: Color = Color(0xFF8B4513), // Brown like coffee
    steamColor: Color = Color.White.copy(alpha = 0.7f),
    size: Dp = 56.dp,
    animationDuration: Int = 700
) {
    var animate by remember { mutableStateOf(false) }
    val shakeRotation by animateFloatAsState(
        targetValue = if (animate) 5f else -5f,
        animationSpec = keyframes {
            durationMillis = animationDuration
            -5f at 0
            5f at animationDuration / 3
            -5f at 2 * animationDuration / 3
            0f at animationDuration
        },
        finishedListener = { animate = false }
    )
    val steamOffset by animateFloatAsState(
        targetValue = if (animate) -10f else 0f,
        animationSpec = tween(animationDuration)
    )

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(buttonColor)
            .rotate(shakeRotation)
            .clickable {
                animate = true
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .offset(y = steamOffset.dp)
                .clip(CircleShape)
                .background(steamColor)
        )
        if (icon != null) {
            Icon(icon, contentDescription = "Coffee Cup", tint = Color.White)
        }
    }
}


@Composable
fun HeartBeatButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Favorite,
    buttonColor: Color = Color.Red,
    pulseColor: Color = Color.Red.copy(alpha = 0.4f),
    size: Dp = 56.dp,
    animationDuration: Int = 600
) {
    var animate by remember { mutableStateOf(false) }
    val pulseScale by animateFloatAsState(
        targetValue = if (animate) 1.4f else 1f,
        animationSpec = keyframes {
            durationMillis = animationDuration
            1f at 0
            1.4f at animationDuration / 3
            1f at 2 * animationDuration / 3
            1.4f at animationDuration
        },
        finishedListener = { animate = false }
    )

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(pulseColor)
            .scale(pulseScale)
    ) {
        Box(
            modifier = Modifier
                .size(size - 8.dp)
                .clip(CircleShape)
                .background(buttonColor)
                .clickable {
                    animate = true
                    onClick()
                }
                .align(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = "Heart Beat", tint = Color.White)
        }
    }
}


















