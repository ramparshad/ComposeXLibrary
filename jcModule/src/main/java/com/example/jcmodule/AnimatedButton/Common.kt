package com.example.jcmodule.AnimatedButton

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class IconPlacement {
    START, END
}

@Composable
fun IconContent(
    text: String,
    icon: @Composable () -> Unit,
    iconPlacement: IconPlacement = IconPlacement.START,
    spacing: Dp = 8.dp,
    textColor: Color = Color.White
) {
    when (iconPlacement) {
        IconPlacement.START -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                icon()
                Spacer(modifier = Modifier.width(spacing))
                Text(text, color = textColor)
            }
        }
        IconPlacement.END -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text, color = textColor)
                Spacer(modifier = Modifier.width(spacing))
                icon()
            }
        }
    }
}

@Composable
fun IconContent(
    text: String,
    iconImage: ImageVector,
    contentDescription: String? = null,
    iconPlacement: IconPlacement = IconPlacement.START,
    spacing: Dp = 8.dp,
    textColor: Color = Color.White,
    iconTint: Color = Color.White
) {
    IconContent(
        text = text,
        icon = {
            Icon(
                imageVector = iconImage,
                contentDescription = contentDescription,
                tint = iconTint
            )
        },
        iconPlacement = iconPlacement,
        spacing = spacing,
        textColor = textColor
    )
}