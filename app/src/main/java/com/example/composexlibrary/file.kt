package com.example.composexlibrary

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcmodule.JCAnimatedProgress

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun fksdn(modifier: Modifier = Modifier) {

    Box(modifier=Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    JCAnimatedProgress()
}}


