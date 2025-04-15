package com.example.jcmodule.TextFeilds

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NeonTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Enter text...",
    textColor: Color = Color.White,
    neonColor: Color = Color.Cyan,
    backgroundColor: Color = Color.LightGray,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    width: Dp = 300.dp,
    animationDuration: Int = 800,
    maxLine: Int = 1,
    singleLine: Boolean = true
) {
    var animate by remember { mutableStateOf(false) }
    val glowAlpha by animateFloatAsState(
        targetValue = if (animate) 0.8f else 0.3f,
        animationSpec = keyframes {
            durationMillis = animationDuration
            0.3f at 0
            0.8f at animationDuration / 4
            0.3f at animationDuration / 2
            0.8f at 3 * animationDuration / 4
            0.3f at animationDuration
        },
        finishedListener = { animate = false }
    )

    Box(
        modifier = modifier
            .width(width)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .border(
                2.dp,
                neonColor.copy(alpha = glowAlpha),
                RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                animate = true
            },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            textStyle = textStyle.copy(color = textColor),
            cursorBrush = SolidColor(neonColor),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = textStyle.copy(color = textColor.copy(alpha = 0.5f))
                    )
                }
                innerTextField()
            }
        )
    }
}



@Composable
fun ModernAnimatedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector? = null,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    maxLine: Int = 1,
    accentColor: Color = MaterialTheme.colorScheme.primary,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isFocused by interactionSource.collectIsFocusedAsState()
    val elevation by animateDpAsState(if (isFocused) 8.dp else 2.dp, animationSpec = tween(200))
    val labelScale by animateFloatAsState(if (isFocused || value.isNotEmpty()) 0.75f else 1f, animationSpec = tween(200))
    val labelOffsetY by animateFloatAsState(
        if (isFocused || value.isNotEmpty()) -24f else 0f,
        animationSpec = tween(200)
    )
    val borderWidth by animateDpAsState(if (isFocused) 2.dp else 1.dp, animationSpec = tween(200))

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(elevation, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .border(borderWidth, accentColor.copy(alpha = if (isFocused) 1f else 0.3f), RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            maxLines = 1,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp
            ),
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            decorationBox = { innerTextField ->
                Box {
                    if (leadingIcon != null) {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = if (isFocused) accentColor else Color.Gray,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(end = 8.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(start = if (leadingIcon != null) 32.dp else 0.dp)
                    ) {
                        innerTextField()
                        Text(
                            text = label,
                            color = if (isFocused) accentColor else Color.Gray,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .offset(y = labelOffsetY.dp)
                                .scale(labelScale)
                                .align(Alignment.TopStart)
                        )
                    }
                }
            }
        )
    }
}


@Composable
fun NeonGlowTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Enter text",
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    glowColor: Color = Color.Cyan,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    animationDuration: Int = 600,
    cornerRadius: Dp = 8.dp,
    minHeight: Dp = 48.dp,
    minWidth: Dp = 200.dp
) {
    var isFocused by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }
    val glowAlpha by animateFloatAsState(
        targetValue = if (isFocused) 0.7f else 0.2f,
        animationSpec = tween(animationDuration, easing = FastOutSlowInEasing)
    )

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = minHeight)
                .widthIn(min = minWidth)
                .clip(RoundedCornerShape(cornerRadius))
                .background(backgroundColor)
                .border(
                    width = 2.dp,
                    color = if (isError) Color.Red else glowColor.copy(alpha = glowAlpha),
                    shape = RoundedCornerShape(cornerRadius)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                leadingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "Leading Icon",
                        tint = textColor,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty() && !isFocused) {
                        Text(
                            text = label,
                            color = textColor.copy(alpha = 0.5f),
                            fontSize = 16.sp
                        )
                    }
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = textColor, fontSize = 16.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                        visualTransformation = if (isPassword && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
                        singleLine = true,
                        cursorBrush = SolidColor(glowColor),
                        interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    isFocused = it is androidx.compose.foundation.interaction.FocusInteraction.Focus
                                }
                            }
                        }
                    )
                }
                trailingIcon?.let {
                    Icon(
                        imageVector = if (isPassword) {
                            if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        } else it,
                        contentDescription = "Trailing Icon",
                        tint = textColor,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                enabled = isPassword,
                                onClick = { showPassword = !showPassword }
                            )
                    )
                }
            }
        }
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
            )
        }
    }
}


@Composable
fun BubbleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Enter text",
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    bubbleColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    animationDuration: Int = 400,
    cornerRadius: Dp = 12.dp,
    minHeight: Dp = 48.dp,
    minWidth: Dp = 200.dp
) {
    var isFocused by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }
    var animateBubble by remember { mutableStateOf(false) }
    val bubbleScale by animateFloatAsState(
        targetValue = if (animateBubble) 1.1f else 1f,
        animationSpec = tween(animationDuration, easing = FastOutSlowInEasing),
        finishedListener = { animateBubble = false }
    )

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = minHeight)
                .widthIn(min = minWidth)
                .scale(bubbleScale)
                .clip(RoundedCornerShape(cornerRadius))
                .background(backgroundColor)
                .border(
                    width = 1.5.dp,
                    color = if (isError) Color.Red else bubbleColor,
                    shape = RoundedCornerShape(cornerRadius)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                leadingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "Leading Icon",
                        tint = textColor,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty() && !isFocused) {
                        Text(
                            text = label,
                            color = textColor.copy(alpha = 0.5f),
                            fontSize = 16.sp
                        )
                    }
                    BasicTextField(
                        value = value,
                        onValueChange = {
                            onValueChange(it)
                            animateBubble = true
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = textColor, fontSize = 16.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                        visualTransformation = if (isPassword && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
                        singleLine = true,
                        cursorBrush = SolidColor(bubbleColor),
                        interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    isFocused = it is androidx.compose.foundation.interaction.FocusInteraction.Focus
                                }
                            }
                        }
                    )
                }
                trailingIcon?.let {
                    Icon(
                        imageVector = if (isPassword) {
                            if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        } else it,
                        contentDescription = "Trailing Icon",
                        tint = textColor,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                enabled = isPassword,
                                onClick = { showPassword = !showPassword }
                            )
                    )
                }
            }
        }
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
            )
        }
    }
}



@Composable
fun ScannerTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Enter text",
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    scannerColor: Color = Color.Green,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    animationDuration: Int = 800,
    cornerRadius: Dp = 8.dp,
    minHeight: Dp = 48.dp,
    minWidth: Dp = 200.dp
) {
    var isFocused by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }
    val scannerOffset by animateFloatAsState(
        targetValue = if (isFocused) 1f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = minHeight)
                .widthIn(min = minWidth)
                .clip(RoundedCornerShape(cornerRadius))
                .background(backgroundColor)
                .border(
                    width = 1.dp,
                    color = if (isError) Color.Red else Color.Gray,
                    shape = RoundedCornerShape(cornerRadius)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            if (isFocused) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .offset(x = ((-0.5f + scannerOffset) * 100).dp)
                        .background(scannerColor.copy(alpha = 0.5f))
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                leadingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "Leading Icon",
                        tint = textColor,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty() && !isFocused) {
                        Text(
                            text = label,
                            color = textColor.copy(alpha = 0.5f),
                            fontSize = 16.sp
                        )
                    }
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = textColor, fontSize = 16.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                        visualTransformation = if (isPassword && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
                        singleLine = true,
                        cursorBrush = SolidColor(scannerColor),
                        interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    isFocused = it is androidx.compose.foundation.interaction.FocusInteraction.Focus
                                }
                            }
                        }
                    )
                }
                trailingIcon?.let {
                    Icon(
                        imageVector = if (isPassword) {
                            if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        } else it,
                        contentDescription = "Trailing Icon",
                        tint = textColor,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                enabled = isPassword,
                                onClick = { showPassword = !showPassword }
                            )
                    )
                }
            }
        }
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
            )
        }
    }
}








