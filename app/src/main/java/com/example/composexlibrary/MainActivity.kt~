package com.example.composexlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composexlibrary.ui.theme.ComposeXLibraryTheme
import com.example.jcmodule.JCBouncingLoader
import com.example.jcmodule.JCCyberpunkLoader
import com.example.jcmodule.JCDNALoader
import com.example.jcmodule.JCEqualizerLoader
import com.example.jcmodule.JCHolographicLoader
import com.example.jcmodule.JCLiquidProgress
import com.example.jcmodule.JCNNLoader
import com.example.jcmodule.JCOrbitLoader
import com.example.jcmodule.JCSquareLoader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeXLibraryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ){
                        simpleUi()
                    }
                }
            }
        }
    }
}

@Composable
fun simpleUi(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        JCBouncingLoader()
        JCNNLoader()
        JCLiquidProgress()
        JCDNALoader()
        JCOrbitLoader()
        JCCyberpunkLoader()
        JCEqualizerLoader()
        JCHolographicLoader()
        JCSquareLoader()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeXLibraryTheme {
        simpleUi()
    }
}