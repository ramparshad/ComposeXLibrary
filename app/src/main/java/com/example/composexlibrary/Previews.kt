package com.example.composexlibrary

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcmodule.AnimatedButton.BlinkButton
import com.example.jcmodule.AnimatedButton.CoffeeCupButton
import com.example.jcmodule.AnimatedButton.DoorLockButton
import com.example.jcmodule.AnimatedButton.FadeButton
import com.example.jcmodule.AnimatedButton.HeartBeatButton
import com.example.jcmodule.AnimatedButton.RewardButton
import com.example.jcmodule.AnimatedButton.RotateButton
import com.example.jcmodule.AnimatedButton.ScaleButton
import com.example.jcmodule.AnimatedButton.ShakeButton


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun review(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        ScaleButton(
            onClick = { /*TODO*/ },
            text = "Scale Button",
            textColor = Color.White,
            containerColor = Color.Blue,
            disabledContainerColor = Color.Gray.copy(alpha = 0.5f),
            elevation = ButtonDefaults.buttonElevation(),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(2.dp, Color.Red),
            icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite") },
            // icon = null               if you not wanna use icon
            animationDuration = 200,
            scaleFactor = 0.95f,
            enabled = true,
            interactionSource = remember { MutableInteractionSource() }
        )

        RotateButton(
            onClick = { /*TODO*/ },
            text = "RotateButton",
            textColor = Color.White,
            containerColor = Color.Red,
            disabledContainerColor = Color.Gray.copy(alpha = 0.5f),
            elevation = ButtonDefaults.buttonElevation(),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(2.dp, Color.Green),
            icon = { Icon(imageVector = Icons.Default.Face, contentDescription = "Favorite") },
            // icon = null               if you not wanna use icon
            animationDuration = 500,
            rotationDegrees = 360f,
            enabled = true,
            interactionSource = remember { MutableInteractionSource() }
        )
        FadeButton(
            onClick = { /*TODO*/ },
            text = "FadeButton",
            textColor = Color.White,
            containerColor = Color.Magenta,
            disabledContainerColor = Color.Gray.copy(alpha = 0.5f),
            elevation = ButtonDefaults.buttonElevation(),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, Color.Black),
            icon = { Icon(imageVector = Icons.Default.Refresh, contentDescription = "Favorite") },
            // icon = null               if you not wanna use icon
            animationDuration = 200,
            fadeToOpacity = 0.5f,
            enabled = true,
            interactionSource = remember { MutableInteractionSource() }
        )
        ShakeButton(
            onClick = { /*TODO*/ },
            text = "ShakeButton",
            textColor = Color.White,
            containerColor = Color.Gray,
            disabledContainerColor = Color.Gray.copy(alpha = 0.5f),
            elevation = ButtonDefaults.buttonElevation(),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, Color.Black),
            icon = { Icon(imageVector = Icons.Default.Build, contentDescription = "Favorite") },
            // icon = null               if you not wanna use icon
            animationDuration = 500,
            shakeDistance = 10.dp,
            shakeCount = 3,
            enabled = true,
            interactionSource = remember { MutableInteractionSource() }
        )

        RewardButton(
            onClick = { println("Reward claimed!") },
            text = "Get Bonus Coins",
            modifier = Modifier.height(70.dp).width(250.dp)
        )

        BlinkButton(
            onClick = { /*TODO*/ },
            icon = Icons.Default.ThumbUp,
            modifier = modifier,
            buttonColor = Color(0xFF4CAF50),
            size = 56.dp,
            animationDuration = 600,
            glowColor = Color(0xFF2E7D32)
        )

        DoorLockButton(
            onClick = { /*TODO*/ },
            modifier = modifier,
            icon = Icons.Default.Lock,
            buttonColor = MaterialTheme.colorScheme.secondary,
            lockColor = Color.Green,
            size = 56.dp,
            animationDuration = 500,
        )

        CoffeeCupButton(
            onClick = { /*TODO*/ },
            icon = Icons.Default.ShoppingCart,
            buttonColor = Color(0xFF8B4513),
            steamColor = Color.White.copy(alpha = 0.7f),
            size = 56.dp,
            animationDuration = 700,
            modifier = modifier,

        )

        HeartBeatButton(
            onClick = { /*TODO*/ },
            modifier = modifier,
            icon = Icons.Default.Favorite,
            buttonColor = Color.Red,
            pulseColor = Color.Red.copy(alpha = 0.4f),
            size = 56.dp,
            animationDuration = 600,
        )
    }
}
