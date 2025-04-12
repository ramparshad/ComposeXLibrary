package com.example.jcmodule

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingLabelTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        AnimatedVisibility(
            visible = value.isNotEmpty() || isFocused,
            enter = fadeIn ()+ slideInVertically { -it },
            exit = fadeOut() + slideOutVertically { it }
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isFocused = it.isFocused }
                .focusRequester(focusRequester),
            placeholder = {
                if (value.isEmpty() && !isFocused) {
                    Text(text = label)
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            shape = MaterialTheme.shapes.medium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedPasswordField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val icon = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff

    Column(modifier = modifier) {
        FloatingLabelTextField(
            label = label,
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        IconButton(
            onClick = { isPasswordVisible = !isPasswordVisible },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Composable
fun NeonInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    var isFocused by remember { mutableStateOf(false) }
    val glowIntensity by animateFloatAsState(
        targetValue = if (isFocused) 1f else 0.3f,
        animationSpec = tween(300)
    )

    Box(
        modifier = Modifier
            .shadow(
                elevation = if (isFocused) 16.dp else 12.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = glowIntensity),
                spotColor = MaterialTheme.colorScheme.primary.copy(alpha = glowIntensity)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = Color(0x1A000000),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 25.dp, vertical = 25.dp)
    ) {
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
            )

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { isFocused = it.isFocused },
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
fun TypewriterTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    var cursorVisible by remember { mutableStateOf(true) }
    val cursorAlpha by animateFloatAsState(
        targetValue = if (cursorVisible) 1f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(500),
            repeatMode = RepeatMode.Reverse
        )
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(500)
            cursorVisible = !cursorVisible
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        if (value.isEmpty()) {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.alpha(0.7f)
            )
        }

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
            cursorBrush = SolidColor(
                MaterialTheme.colorScheme.primary.copy(alpha = cursorAlpha)
            )
        )
    }
}


@Composable
fun LiquidFillTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    var isFocused by remember { mutableStateOf(false) }
    val fillProgress by animateFloatAsState(
        targetValue = if (isFocused || value.isNotEmpty()) 1f else 0f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        // Liquid fill background
        Box(
            modifier = Modifier
                .fillMaxWidth(fillProgress)
                .fillMaxHeight()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                        )
                    )
                )
                .clip(RoundedCornerShape(12.dp))
        )

        // Text content
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 18.dp)
                .onFocusChanged { isFocused = it.isFocused },
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        // Floating label
        if (label.isNotEmpty()) {
            Text(
                text = label,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .offset(y = if (fillProgress > 0.5f) (-12).dp else 0.dp)
                    .alpha(fillProgress),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
    isError: Boolean = false,
    errorMessage: String? = null,
    maxLength: Int? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isPassword: Boolean = false
) {
    var isFocused by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    // Animation states
    val labelOffset by animateDpAsState(
        targetValue = if (value.isNotEmpty() || isFocused) (-8).dp else 0.dp,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
    )
    val borderWidth by animateDpAsState(
        targetValue = if (isFocused) 2.dp else 1.dp,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )
    val borderColor by animateColorAsState(
        targetValue = when {
            isError -> MaterialTheme.colorScheme.error
            isFocused -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.outline
        },
        animationSpec = tween(300)
    )

    Column(modifier = modifier) {
        // Floating label with error state
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = if (isError) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .offset(y = labelOffset)
                    .graphicsLayer {
                        alpha = if (value.isNotEmpty() || isFocused) 1f else 0f
                        scaleX = if (value.isNotEmpty() || isFocused) 0.9f else 1f
                        scaleY = if (value.isNotEmpty() || isFocused) 0.9f else 1f
                    }
            )

            // Error message (animated)
            // Fixed AnimatedVisibility with proper expand/shrink parameters
            this@Column.AnimatedVisibility(
                visible = isError && !errorMessage.isNullOrEmpty(),
                enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
                exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Top)
            ) {
                Text(
                    text = errorMessage ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
            }
        }

        // Text field container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Leading icon
                leadingIcon?.invoke()

                // Actual text field
                BasicTextField(
                    value = value,
                    onValueChange = {
                        if (maxLength == null || it.length <= maxLength) {
                            onValueChange(it)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged { isFocused = it.isFocused },
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    keyboardOptions = if (isPassword) {
                        keyboardOptions.copy(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        )
                    } else keyboardOptions,
                    keyboardActions = keyboardActions,
                    visualTransformation = if (isPassword && !passwordVisible) {
                        PasswordVisualTransformation()
                    } else VisualTransformation.None,
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box {
                            if (value.isEmpty() && !isFocused) {
                                Text(
                                    text = placeholder,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                // Trailing icons (password toggle or custom)
                if (isPassword) {
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility
                            else Icons.Default.VisibilityOff,
                            contentDescription = if (passwordVisible) "Hide password"
                            else "Show password",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    trailingIcon?.invoke()
                }
            }
        }

        // Character counter (optional)
        maxLength?.let {
            Text(
                text = "${value.length}/$maxLength",
                style = MaterialTheme.typography.labelSmall,
                color = if (value.length > maxLength) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FloatingLabelTextFieldPreview() {
    var text by remember { mutableStateOf("") }

    Column {
        FloatingLabelTextField(
            label = "Enter text",
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.padding(16.dp)
        )
        AnimatedPasswordField(
            label = "Password",
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.padding(16.dp)
        )
        AdvancedTextField(
            value = text,
            onValueChange = { text = it },
            label = "Advanced Text Field",
            placeholder = "Enter text",
            isError = text.length > 10,
            errorMessage = if (text.length > 10) "Max length is 10" else null,
            maxLength = 10,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "Leading Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.VisibilityOff,
                    contentDescription = "Trailing Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            modifier = Modifier.padding(16.dp)
        )


        NeonInputField(
            value = text,
            onValueChange = { text = it },
            label = "Neon Input Field"
        )

        TypewriterTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = "Type here...",
        )

        LiquidFillTextField(
            value = text,
            onValueChange = { text = it },
            label = "Liquid Fill Text Field"
        )



    }
}