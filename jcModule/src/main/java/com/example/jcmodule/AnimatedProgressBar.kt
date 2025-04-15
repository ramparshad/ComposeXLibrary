package com.example.jcmodule

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

@Composable
fun JCAnimatedProgress(
    modifier: Modifier = Modifier,
    progressColors: List<Color> = listOf(Color.Red, Color.Yellow, Color.Green),
    trackColor: Color = Color.Gray.copy(alpha = 0.3f),
    backgroundColor: Color = Color.Transparent,
    strokeWidth: Dp = 8.dp,
    size: Dp = 60.dp,
    animationDuration: Int = 5000,
    progressValue: Float? = null,
    shape: Shape = CircleShape,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 0.dp
) {
    // Sanitize inputs to prevent errors
    val safeSize = max(24.dp, size) // Minimum size to avoid rendering issues
    val safeStrokeWidth = min(max(2.dp, strokeWidth), safeSize / 4) // Ensure stroke fits
    val safeBorderWidth = min(borderWidth, safeSize / 8) // Limit border width
    val safeProgress = progressValue?.let { min(max(0f, it), 1f) } // Clamp progress
    val safeDuration = max(100, animationDuration) // Prevent too fast animation
    val safeColors = if (progressColors.isEmpty()) listOf(Color.Gray) else progressColors

    // Animation for indeterminate mode
    val infiniteTransition = rememberInfiniteTransition(label = "progressAnimation")
    val animatedProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = safeDuration,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "progress"
    )

    // Use provided progressValue or animated progress
    val finalProgress = safeProgress ?: animatedProgress

    // Color transition based on progress
    val color by animateColorAsState(
        targetValue = when {
            finalProgress < 0.3f -> safeColors.getOrElse(0) { Color.Gray }
            finalProgress < 0.7f -> safeColors.getOrElse(1) { safeColors[0] }
            else -> safeColors.getOrElse(2) { safeColors.getOrElse(1) { safeColors[0] } }
        },
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "color"
    )

    Box(
        modifier = modifier
            .size(safeSize)
            .clip(shape)
            .background(backgroundColor, shape)
            .run {
                if (safeBorderWidth > 0.dp) {
                    this.border(safeBorderWidth, borderColor, shape)
                } else {
                    this
                }
            },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = { finalProgress },
            color = color,
            trackColor = trackColor,
            strokeWidth = safeStrokeWidth,
            modifier = Modifier.size(safeSize - safeBorderWidth * 2)
        )
    }
}

@Composable
fun BouncingDotsLoader(
    modifier: Modifier = Modifier,
    dotCount: Int = 3,
    dotSize: Dp = 12.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    animationDuration: Int = 600,
    spaceBetween: Dp = 8.dp
) {
    val delays = remember { List(dotCount) { it * 100 } }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween),
        verticalAlignment = Alignment.CenterVertically
    ) {
        delays.forEach { delayMillis ->
            val infiniteTransition = rememberInfiniteTransition()
            val scale by infiniteTransition.animateFloat(
                initialValue = 0.3f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = animationDuration
                        0.3f at 0
                        1f at animationDuration / 2
                        0.3f at animationDuration
                    },
                    repeatMode = RepeatMode.Reverse
                ),
                label = "bounce_animation_$delayMillis"
            )

            Box(
                modifier = Modifier
                    .size(dotSize)
                    .graphicsLayer { scaleX = scale; scaleY = scale }
                    .background(color, CircleShape)
            )
        }
    }
}


@Composable
fun SpinningArcProgressBar(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    strokeWidth: Dp = 4.dp,
    color: Color = Color.Blue
) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = modifier.size(size)) {
        val radius = size.toPx() / 2
        drawArc(
            color = color,
            startAngle = angle,
            sweepAngle = 90f,
            useCenter = false,
            topLeft = Offset(strokeWidth.toPx() / 2, strokeWidth.toPx() / 2),
            size = Size(size.toPx() - strokeWidth.toPx(), size.toPx() - strokeWidth.toPx()),
            style = Stroke(width = strokeWidth.toPx())
        )
    }
}


@Composable
fun PulsingCircleProgressBar(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    color: Color = Color.Green
) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = modifier.size(size)) {
        drawCircle(
            color = color,
            radius = (size.toPx() / 2) * scale,
            center = Offset(size.toPx() / 2, size.toPx() / 2),
            alpha = 1f - (scale - 0.8f)
        )
    }
}



@Composable
fun WaveProgressBar(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    color: Color = Color.Cyan
) {
    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = modifier.size(size)) {
        val radius = size.toPx() / 2
        for (i in 0 until 360 step 30) {
            val angle = Math.toRadians((i + offset).toDouble())
            val x = radius + cos(angle) * radius * 0.4
            val y = radius + sin(angle) * radius * 0.4
            drawCircle(
                color = color,
                radius = 4.dp.toPx(),
                center = Offset(x.toFloat(), y.toFloat())
            )
        }
    }
}



@Composable
fun GrowingDotsProgressBar(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    color: Color = Color.Magenta
) {
    val infiniteTransition = rememberInfiniteTransition()
    val scales = List(5) { index ->
        infiniteTransition.animateFloat(
            initialValue = 0.5f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 600,
                    delayMillis = index * 100,
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Canvas(modifier = modifier.size(size)) {
        val spacing = size.toPx() / 6
        scales.forEachIndexed { index, scale ->
            drawCircle(
                color = color,
                radius = 4.dp.toPx() * scale.value,
                center = Offset(spacing * (index + 1), size.toPx() / 2)
            )
        }
    }
}




@Composable
fun OrbitingPlanetsProgressBar(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    color: Color = Color.Yellow
) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = modifier.size(size)) {
        val center = size.toPx() / 2
        drawCircle(
            color = color.copy(alpha = 0.3f),
            radius = center * 0.6f,
            center = Offset(center, center),
            style = Stroke(width = 7.dp.toPx())
        )
        for (i in 0 until 3) {
            val planetAngle = angle + (i * 120f)
            val x = center + cos(Math.toRadians(planetAngle.toDouble())).toFloat() * center * 0.6f
            val y = center + sin(Math.toRadians(planetAngle.toDouble())).toFloat() * center * 0.6f
            drawCircle(
                color = color,
                radius = 4.dp.toPx(),
                center = Offset(x, y)
            )
        }
    }
}



@Composable
fun RotatingPetalsProgressBar(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    color: Color = Color.Green
) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = modifier.size(size)) {
        val center = size.toPx() / 2
        for (i in 0 until 8) {
            rotate((angle + i * 45f)) {
                drawOval(
                    color = color,
                    topLeft = Offset(center - 4.dp.toPx(), center - 12.dp.toPx()),
                    size = Size(8.dp.toPx(), 24.dp.toPx())
                )
            }
        }
    }
}


@Composable
fun RippleEffectProgressBar(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    color: Color = Color.Blue
) {
    val infiniteTransition = rememberInfiniteTransition()
    val scales = List(3) { index ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1200,
                    delayMillis = index * 400,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Canvas(modifier = modifier.size(size)) {
        val center = size.toPx() / 2
        scales.forEach { scale ->
            drawCircle(
                color = color.copy(alpha = 1f - scale.value),
                radius = (size.toPx() / 2) * scale.value,
                center = Offset(center, center),
                style = Stroke(width = 2.dp.toPx())
            )
        }
    }
}


@Composable
fun BouncingBallProgressBar(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    color: Color = Color.Magenta
) {
    val infiniteTransition = rememberInfiniteTransition()
    val position by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = modifier.size(size)) {
        val ballRadius = 6.dp.toPx()
        val x = size.toPx() * position
        val y = size.toPx() / 2
        drawCircle(
            color = color,
            radius = ballRadius,
            center = Offset(x, y)
        )
    }
}


@Composable
fun SpiralProgressBar(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    color: Color = Color.Yellow
) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = modifier.size(size)) {
        val center = size.toPx() / 2
        val points = mutableListOf<Offset>()
        for (i in 0 until 360 step 10) {
            val rad = Math.toRadians((i + angle).toDouble())
            val radius = (i / 360f) * center * 0.8f
            val x = center + cos(rad) * radius
            val y = center + sin(rad) * radius
            points.add(Offset(x.toFloat(), y.toFloat()))
        }
        points.forEachIndexed { index, point ->
            drawCircle(
                color = color.copy(alpha = index / points.size.toFloat()),
                radius = 2.dp.toPx(),
                center = point
            )
        }
    }
}