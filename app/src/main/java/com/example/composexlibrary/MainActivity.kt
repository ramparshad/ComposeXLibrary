package com.example.composexlibrary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composexlibrary.ui.theme.ComposeXLibraryTheme
import com.example.jcmodule.ActionItem
import com.example.jcmodule.BouncingBallProgressBar
import com.example.jcmodule.BouncingDotsLoader
import com.example.jcmodule.DataClassBottomAppBar.BounceBottomBar
import com.example.jcmodule.DataClassBottomAppBar.BounceBottomBarItem
import com.example.jcmodule.DataClassBottomAppBar.defaultBounceBottomBarItemsWithSelectedIcons
import com.example.jcmodule.GrowingDotsProgressBar
import com.example.jcmodule.JCAnimatedProgress
import com.example.jcmodule.LiquidMotionAppBar
import com.example.jcmodule.OrbitingPlanetsProgressBar
import com.example.jcmodule.PulsingCircleProgressBar
import com.example.jcmodule.RippleEffectProgressBar
import com.example.jcmodule.RotatingPetalsProgressBar
import com.example.jcmodule.SpinningArcProgressBar
import com.example.jcmodule.SpiralProgressBar
import com.example.jcmodule.TextFeilds.ReviewTextField
import com.example.jcmodule.WaveProgressBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeXLibraryTheme {
                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.padding()
                    ){*/
                        review()
                    }
                }
            }
        }
    /*}
}*/


// ------------- UI ---------------------------
// progress bar completes
@Composable
fun SimpleUi(modifier: Modifier = Modifier) {

    Spacer(modifier=Modifier.padding(10.dp))
    LazyVerticalGrid(
        columns = GridCells.Fixed(1), // 2 columns
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "JCAnimatedProgress()",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, color = Color.Blue
                )
                JCAnimatedProgress(
                    modifier = Modifier.size(100.dp),
                    progressColors = listOf(
                        Color.Red,
                        Color.Yellow,
                        Color.Green
                    ),
                    trackColor = Color.Gray,
                    backgroundColor = Color.Transparent,
                    strokeWidth = 8.dp,
                    size = 100.dp,
                    animationDuration = 2000,
                    progressValue = null,
                    shape = androidx.compose.foundation.shape.CircleShape,
                    borderColor = Color.Transparent,
                    borderWidth = 0.dp
                )
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "BouncingDotsLoader()",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, color = Color.Blue
                )
                BouncingDotsLoader(
                    modifier = Modifier.size(100.dp),
                    dotCount = 5,
                    dotSize = 12.dp,
                    color = Color.Red,
                    animationDuration = 600,
                    spaceBetween = 8.dp
                )
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "SpringArcProgressBar()",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, color = Color.Blue
                )
                SpinningArcProgressBar(
                    modifier = Modifier.size(80.dp),
                    size = 80.dp,
                    strokeWidth = 6.dp,
                    color = Color.Red
                )
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "PulsingCircleProgressBar()",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, color = Color.Blue
                )
                PulsingCircleProgressBar(
                    modifier = Modifier.size(80.dp),
                    size = 80.dp,
                    color = Color.Green
                )
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "WaveProgressBar()",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, color = Color.Blue
                )
                WaveProgressBar(
                    modifier = Modifier.size(120.dp),
                    size = 140.dp,
                    color = Color.Black
                )
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "GrowingDotsProgressBar()",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, color = Color.Blue
                )
                GrowingDotsProgressBar(
                    modifier = Modifier.size(80.dp),
                    size = 80.dp,
                    color = Color.Magenta
                )
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "OrbitingPlanetsProgressBar()",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, color = Color.Blue
                )
                OrbitingPlanetsProgressBar(
                    modifier = Modifier.size(80.dp),
                    size = 80.dp,
                    color = Color.Red
                )
            }
        }
        item {
            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "RotatingPetalsProgressBar()",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, color = Color.Blue)
            RotatingPetalsProgressBar(
                modifier = Modifier.size(80.dp),
                size = 120.dp,
                color = Color.Green
            )
        }
        }
        item {
            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "RippleEffectProgressBar()",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, color = Color.Blue)
            RippleEffectProgressBar(
                modifier = Modifier.size(80.dp),
                size = 80.dp,
                color = Color.Blue
            )
        }}
        item {
            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "BouncingBallProgressBar()",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, color = Color.Blue)
            BouncingBallProgressBar(
                modifier = Modifier.size(80.dp),
                size = 80.dp,
                color = Color.Magenta
            )
        }
    }
        item {
            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "SpiralProgressBar()",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, color = Color.Blue)
            SpiralProgressBar(
                modifier = Modifier.size(80.dp),
                size = 80.dp,
                color = Color.Red
            )
        }
    }
}
}
// --------------------------------------------------



// -------------Customized bottomAppBar ---------------------------
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Customized(){
    MaterialTheme {
        var selectedIndex by remember { mutableStateOf(0) }
        Scaffold(
            bottomBar = {
                    BounceBottomBar(
                        selectedIndex = selectedIndex,
                        onItemSelected = { selectedIndex = it },
                        items = listOf(
                            BounceBottomBarItem(Icons.Filled.Person, "person"),
                            BounceBottomBarItem(Icons.Filled.ShoppingCart, "Shop"),
                            BounceBottomBarItem(Icons.Filled.Delete, "delete"),
                            BounceBottomBarItem(Icons.Filled.Settings, "Settings")
                        ),
                        bounceScale = 1.6f,
                        selectedColor = Color.Blue,
                        containerColor = Color.LightGray,
                        showLabels = false,
                        barHeight = 70.dp
                    )

                /*ExpandableLabelBottomBar(
                    selectedIndex = selectedIndex,
                    onItemSelected = { selectedIndex = it },
                    items = listOf(
                        ExpandableBottomBarItem(Icons.Filled.Person, "person"),
                        ExpandableBottomBarItem(Icons.Filled.ShoppingCart, "Shop"),
                        ExpandableBottomBarItem(Icons.Filled.Delete, "delete"),
                        ExpandableBottomBarItem(Icons.Filled.Settings, "Settings")
                    ),
                    selectedColor = Color.Magenta,
                    containerColor = Color.LightGray,
                    barHeight = 70.dp
                )*/
            }
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Selected Index: $selectedIndex",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
//-------------------------------------------------------


//-------------------default bottom bars------------------------------------
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ByDefault() {
    ComposeXLibraryTheme {
        var selectedIndex by remember { mutableStateOf(0) }

        Scaffold(
            bottomBar = {
                BounceBottomBar(
                    selectedIndex = selectedIndex,
                    onItemSelected = { selectedIndex = it },
                    items = defaultBounceBottomBarItemsWithSelectedIcons()
                )

                /*ExpandableLabelBottomBar(
                    selectedIndex = selectedIndex,
                    onItemSelected = { selectedIndex = it },
                    items = defaultExpandableBottomBarItemsWithSelectedIcons(),
                )*/
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Selected Index: $selectedIndex",
                )
            }
        }
    }
}
//--------------------------------------------------------


//---------------------defalt top bar-----------------------------------
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
private fun defaultTop() {

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            LiquidMotionAppBar(
                scrollState = scrollState,
                title = "TopBar",
                onActionClick = { /* Handle actions */ },
                onLeadingIconClick = { /*navController.popBackStack()*/ } ,
                leadingIconSize = 28.dp,
                leadingIcon = Icons.Default.ArrowBack,
                leadingIconColor = Color.White,

                // Title Alignment
                titleVerticalAlignment = Alignment.CenterVertically,
                titlePadding = PaddingValues(top = 4.dp),

                // Visual Customization
                initialHeight = 120.dp,
                collapsedHeight = 80.dp,
                startColor = Color(0xD26A6574),
                endColor = Color(0xFF03DAC6),
                titleStyle = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold, color = Color.White
                ),

                // Actions Customization
                actions = listOf(
                    ActionItem(
                        id = "custom",
                        icon = Icons.Default.Settings,
                        label = "Settings",
                        iconColor = Color.White,
                        containerColor = Color.Gray
                    ),
                    ActionItem(
                        id = "custom2",
                        icon = Icons.Default.Add,
                        label = "Settings",
                        iconColor = Color.White,
                        containerColor = Color.Gray
                    ),
                    ActionItem(
                        id = "custom2",
                        icon = Icons.Default.Lock,
                        label = "Settings",
                        iconColor = Color.White,
                        containerColor = Color.Gray
                    )
                )
            )
        }
    ){ _ -> }
}
