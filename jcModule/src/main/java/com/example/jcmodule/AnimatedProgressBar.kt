package com.example.jcmodule

import androidx.compose.animation.core.Animatable
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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

/**
 * An animated progress bar with customizable colors and animation
 * @param progress Target progress value between 0 and 1
 * @param modifier Modifier for the progress bar
 * @param backgroundColor Color of the progress bar track
 * @param progressColor Color of the progress indicator
 * @param animationDuration Duration of progress animation in milliseconds
 */

@Composable
fun textUI(){
    Text(text = "successfull", fontSize = 30.sp)
}


/*@Composable
fun JCAnimatedProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.LightGray,
    progressColor: Color = Color.Blue,
    animationDuration: Int = 1000
) {
    // Validate progress value
    val validatedProgress = progress.coerceIn(0f, 1f)

    // Animated progress value
    val animatedProgress = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    // Animate when progress changes
    LaunchedEffect(validatedProgress) {
        coroutineScope.launch {
            animatedProgress.animateTo(
                targetValue = validatedProgress,
                animationSpec = tween(durationMillis = animationDuration)
            )
        }

        Column (modifier = modifier.padding(vertical = 8.dp)) {
            LinearProgressIndicator(
                progress = animatedProgress.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = progressColor,
                trackColor = backgroundColor
            )
        }
    }
}*/
@Composable
fun JCBouncingLoader(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "bounce")
    val scales = List(3) { index ->
        infiniteTransition.animateFloat(
            initialValue = 0.3f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, delayMillis = index * 150, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "scale$index"
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                scales.forEach { scale ->
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .scale(scale.value)
                            .background(Color.Blue, CircleShape)
                    )
                }
            }
        }
    }
}

@Composable
fun JCSquareLoader() {
    val infiniteTransition = rememberInfiniteTransition()

    Card(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.Absolute.Center) {

            val rotation by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(2000, easing = LinearEasing)
                )
            )


            Box(
                modifier = Modifier
                    .size(50.dp)
                    .rotate(rotation)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color.Blue, Color.Cyan)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
    }
}

@Composable
fun JCEqualizerLoader() {
    val infiniteTransition = rememberInfiniteTransition()
    Card(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.Absolute.Center
        ) {
            val heights = listOf(
                infiniteTransition.animateFloat(
                    initialValue = 10f,
                    targetValue = 40f,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = 1200
                            10f at 0 with LinearEasing
                            40f at 200 with FastOutSlowInEasing
                            10f at 400 with LinearEasing
                            20f at 600 with FastOutSlowInEasing
                            10f at 800 with LinearEasing
                            30f at 1000 with FastOutSlowInEasing
                            10f at 1200 with LinearEasing
                        },
                        repeatMode = RepeatMode.Restart
                    )
                ),
                infiniteTransition.animateFloat(
                    initialValue = 30f,
                    targetValue = 10f,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = 1200
                            30f at 0 with LinearEasing
                            10f at 200 with FastOutSlowInEasing
                            25f at 400 with LinearEasing
                            10f at 600 with FastOutSlowInEasing
                            35f at 800 with LinearEasing
                            10f at 1000 with FastOutSlowInEasing
                            20f at 1200 with LinearEasing
                        },
                        repeatMode = RepeatMode.Restart
                    )
                ),
                infiniteTransition.animateFloat(
                    initialValue = 20f,
                    targetValue = 35f,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = 1200
                            20f at 0 with LinearEasing
                            35f at 200 with FastOutSlowInEasing
                            10f at 400 with LinearEasing
                            25f at 600 with FastOutSlowInEasing
                            10f at 800 with LinearEasing
                            30f at 1000 with FastOutSlowInEasing
                            10f at 1200 with LinearEasing
                        },
                        repeatMode = RepeatMode.Restart
                    )
                )
            )

            Row(
                modifier = Modifier.height(40.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                heights.forEach { animatedHeight ->
                    Box(
                        modifier = Modifier
                            .width(10.dp)
                            .height(animatedHeight.value.dp)
                            .background(Color.Blue)
                    )
                }
            }
        }
    }
}

@Composable
fun JCOrbitLoader() {
    val dots = 8
    val infiniteTransition = rememberInfiniteTransition()
    Card(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 35.dp),
            horizontalArrangement = Arrangement.Absolute.Center
        ) {
            val rotations = List(dots) { index ->
                infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 360f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(2000, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    )
                )
            }

            Box(modifier = Modifier.size(70.dp)) {
                rotations.forEachIndexed { index, rotation ->
                    val angle = index * (360f / dots)
                    Box(
                        modifier = Modifier
                            .offset {
                                val radius = 30.dp.roundToPx().toFloat()
                                val radian = Math.toRadians((angle + rotation.value).toDouble())
                                val x = (radius * cos(radian)).toFloat()
                                val y = (radius * sin(radian)).toFloat()
                                IntOffset(x.toInt(), y.toInt())
                            }
                            .size(8.dp)
                            .background(Color.Blue, CircleShape)
                    )
                }
            }
        }
    }
}

@Composable
fun JCLiquidProgress() {
    val infiniteTransition = rememberInfiniteTransition()
    Card(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.Absolute.Center
        ) {
            val waveOffset by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 2 * Math.PI.toFloat(),
                animationSpec = infiniteRepeatable(
                    animation = tween(2000, easing = LinearEasing)
                )
            )

            Canvas(modifier = Modifier.fillMaxWidth().height(16.dp)) {
                val path = Path().apply {
                    val amplitude = size.height / 4
                    val frequency = 0.5f

                    moveTo(0f, size.height)
                    lineTo(0f, size.height / 2)

                    for (x in 0..size.width.toInt()) {
                        val y =
                            size.height / 2 + amplitude * sin(waveOffset + x * frequency / size.width * 2 * Math.PI.toFloat())
                        if (x == 0) moveTo(x.toFloat(), y) else lineTo(x.toFloat(), y)
                    }

                    lineTo(size.width, size.height)
                    close()
                }

                drawPath(
                    path = path,
                    color = Color(0xFF6C5CE7),
                    style = Fill
                )
            }
        }
    }
}

@Composable
fun JCHolographicLoader() {
    val infiniteTransition = rememberInfiniteTransition()
    Card(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.Absolute.Center
        ) {
            val offsetX by infiniteTransition.animateFloat(
                initialValue = -1000f,
                targetValue = 1000f,
                animationSpec = infiniteRepeatable(
                    animation = tween(3000, easing = LinearEasing)
                )
            )

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1A1A2E))
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val gradient = Brush.linearGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0x5566F1FF),
                            Color.Transparent
                        ),
                        start = Offset(offsetX, 0f),
                        end = Offset(offsetX + 500f, size.height)
                    )
                    drawRect(brush = gradient)
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF16213E))
                )
            }
        }
    }
}

@Composable
fun JCNNLoader() {
    val nodeCount = 5
    val infiniteTransition = rememberInfiniteTransition()
    Card(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.Absolute.Center
        ) {

            val nodeStates = List(nodeCount) { index ->
                infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = 2500
                            0f at (index * 300) % 2500 with LinearEasing
                            1f at ((index * 300) + 500) % 2500 with FastOutSlowInEasing
                            0f at ((index * 300) + 1000) % 2500 with LinearEasing
                        }
                    )
                )
            }

            Canvas(modifier = Modifier.size(200.dp)) {
                // Draw connections
                for (i in 0 until nodeCount - 1) {
                    drawLine(
                        color = Color(0x5566F1FF),
                        start = Offset(size.width * (i + 0.5f) / nodeCount, size.height * 0.3f),
                        end = Offset(size.width * (i + 1.5f) / nodeCount, size.height * 0.7f),
                        strokeWidth = 2.dp.toPx() * nodeStates[i].value,
                        alpha = nodeStates[i].value
                    )
                }

                // Draw nodes
                nodeStates.forEachIndexed { index, state ->
                    drawCircle(
                        color = Color(0xFF00E0FF),
                        radius = 16.dp.toPx() * state.value,
                        center = Offset(
                            size.width * (index + 0.5f) / nodeCount,
                            if (index % 2 == 0) size.height * 0.3f else size.height * 0.7f
                        ),
                        alpha = state.value
                    )
                }
            }
        }
    }
}

@Composable
fun JCDNALoader() {
    val segmentCount = 12
    val infiniteTransition = rememberInfiniteTransition()
    Card(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.Absolute.Center
        ) {
            val rotation by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(4000, easing = LinearEasing)
                )
            )

            Canvas(modifier = Modifier.size(120.dp)) {
                val center = Offset(size.width / 2, size.height / 2)
                val radius = size.minDimension / 3

                for (i in 0 until segmentCount) {
                    val angle = i * (360f / segmentCount) + rotation
                    val radian = Math.toRadians(angle.toDouble())
                    val x = center.x + radius * cos(radian).toFloat()
                    val y = center.y + radius * sin(radian).toFloat()

                    // Draw connection lines
                    if (i > 0) {
                        val prevAngle = (i - 1) * (360f / segmentCount) + rotation
                        val prevRadian = Math.toRadians(prevAngle.toDouble())
                        val prevX = center.x + radius * cos(prevRadian).toFloat()
                        val prevY = center.y + radius * sin(prevRadian).toFloat()

                        drawLine(
                            color = Color(0xFF6C5CE7),
                            start = Offset(prevX, prevY),
                            end = Offset(x, y),
                            strokeWidth = 2.dp.toPx()
                        )

                        // Draw cross connections
                        if (i % 2 == 0) {
                            val oppositeAngle = angle + 180f
                            val oppositeRadian = Math.toRadians(oppositeAngle.toDouble())
                            val oppositeX = center.x + radius * cos(oppositeRadian).toFloat()
                            val oppositeY = center.y + radius * sin(oppositeRadian).toFloat()

                            drawLine(
                                color = Color(0xFF00E0FF).copy(alpha = 0.6f),
                                start = Offset(x, y),
                                end = Offset(oppositeX, oppositeY),
                                strokeWidth = 1.dp.toPx()
                            )
                        }
                    }

                    // Draw nodes
                    drawCircle(
                        color = if (i % 2 == 0) Color(0xFF6C5CE7) else Color(0xFF00E0FF),
                        radius = 4.dp.toPx(),
                        center = Offset(x, y)
                    )
                }
            }
        }
    }
}

@Composable
fun JCCyberpunkLoader() {
    val infiniteTransition = rememberInfiniteTransition()
    Card(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.Absolute.Center
        ) {
            val scanOffset by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(2000, easing = LinearEasing)
                )
            )

            val gridSize = 20.dp

            Canvas(modifier = Modifier.size(150.dp)) {
                // Draw grid
                for (x in 0..size.width.toInt() step gridSize.toPx().toInt()) {
                    drawLine(
                        color = Color(0x5566F1FF),
                        start = Offset(x.toFloat(), 0f),
                        end = Offset(x.toFloat(), size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }

                for (y in 0..size.height.toInt() step gridSize.toPx().toInt()) {
                    drawLine(
                        color = Color(0x5566F1FF),
                        start = Offset(0f, y.toFloat()),
                        end = Offset(size.width, y.toFloat()),
                        strokeWidth = 1.dp.toPx()
                    )
                }

                // Draw scanning line
                drawLine(
                    color = Color(0xFF00E0FF),
                    start = Offset(0f, size.height * scanOffset),
                    end = Offset(size.width, size.height * scanOffset),
                    strokeWidth = 2.dp.toPx(),
                    alpha = 0.8f
                )

                // Draw active nodes
                for (x in 0..size.width.toInt() step gridSize.toPx().toInt()) {
                    for (y in 0..size.height.toInt() step gridSize.toPx().toInt()) {
                        val distance = abs(y - size.height * scanOffset)
                        if (distance < 30) {
                            val alpha = 1f - (distance / 30f)
                            drawCircle(
                                color = Color(0xFF00E0FF),
                                radius = 4.dp.toPx() * alpha,
                                center = Offset(x.toFloat(), y.toFloat()),
                                alpha = alpha
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CircularProgressWithColorChangePreview() {
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally) {

        JCLiquidProgress()
        JCNNLoader()
        JCCyberpunkLoader()
        JCDNALoader()
        JCHolographicLoader()
        JCEqualizerLoader()
        JCSquareLoader()
        JCOrbitLoader()
        JCBouncingLoader()

    }
}

