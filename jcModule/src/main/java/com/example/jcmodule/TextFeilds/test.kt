package com.example.jcmodule.TextFeilds

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReviewTextField(modifier: Modifier = Modifier) {

    var state by remember { mutableStateOf("") }

    Column(
        modifier = Modifier

            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        NeonTextField(
            value = state,
            onValueChange = { state = it },
            modifier = modifier,
            placeholder = "Enter text...",
            textColor = Color.White,
            neonColor = Color.Blue,
            textStyle = MaterialTheme.typography.bodyMedium,
            width = 300.dp,
            animationDuration = 800,
            maxLine = 1,
            singleLine = true
        )


        ModernAnimatedTextField(
            value = state,
            onValueChange = { state = it },
            label = "Enter Name",
            maxLine = 1,
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.padding(horizontal = 30.dp),
            backgroundColor = MaterialTheme.colorScheme.surface,
            accentColor = MaterialTheme.colorScheme.primary,
            visualTransformation = VisualTransformation.None,
            interactionSource = remember { MutableInteractionSource() }
        )


        BubbleTextField(
            value = state,
            onValueChange = { state = it },
            modifier = Modifier.padding(horizontal = 30.dp),
            label = "Enter Name",
            leadingIcon = Icons.Default.Person,
            trailingIcon = null,
            keyboardType = KeyboardType.Text,
            isPassword = false,
            isError = state.length >= 1 && state.length <2,                                    //isError = state.length < 7,
            errorMessage = "Name is Required",
            bubbleColor = Color.Red,
            textColor = MaterialTheme.colorScheme.onSurface,
            backgroundColor = MaterialTheme.colorScheme.surface,
            animationDuration = 100,
            cornerRadius = 12.dp,
            minHeight = 48.dp,
            minWidth = 200.dp,

            )

         ScannerTextField(
             value = state,
             onValueChange = { state = it },
             modifier = Modifier.padding(horizontal = 30.dp),
             label = "Enter password",
             leadingIcon = Icons.Default.Lock,
             trailingIcon = Icons.Default.RemoveRedEye,
             keyboardType = KeyboardType.Text,
             isPassword = true,
             isError = state.length >= 1 && state.length <7,                                    //isError = state.length < 7,
             errorMessage = "It must be 7 characters or more",
             scannerColor = Color.Green,
             textColor = MaterialTheme.colorScheme.onSurface,
             backgroundColor = MaterialTheme.colorScheme.surface,
             animationDuration = 100,
             cornerRadius = 8.dp,
             minHeight = 48.dp,
             minWidth = 200.dp,

             )
    }
}




