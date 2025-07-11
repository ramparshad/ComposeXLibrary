
# ✨✨ JETPACK COMPOSE COMPONENTS ✨✨

THIS IS A JETPACK COMPOSE COMPONENTS LIBRARY IN WHICH MULTIPLE COMPONENTS LIKE ProgressBar, TopAppBar, BottomAppBar etc. are implemented.


## ✨ Installation

Step 1. Add the JitPack repository to your root settings.gradle  :

```bash
  dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency

```bash
dependencies {
	        implementation 'com.github.ramparshad:ComposeXLibrary:1.9.2'
	}
```
    
## ✨✨ 1. Animated ProgressBars 


https://github.com/user-attachments/assets/1e9dfb60-f738-4a5a-b610-f0cc7b30e4f7


```bash
  
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
```


```bash
BouncingDotsLoader(
                    modifier = Modifier.size(100.dp),
                    dotCount = 5,
                    dotSize = 12.dp,
                    color = Color.Red,
                    animationDuration = 600,
                    spaceBetween = 8.dp
                )

```



```bash
SpinningArcProgressBar(
                    modifier = Modifier.size(80.dp),
                    size = 80.dp,
                    strokeWidth = 6.dp,
                    color = Color.Red
                )

```


```bash
PulsingCircleProgressBar()

```


```bash
WaveProgressBar()

```

```bash
 GrowingDotsProgressBar()

```


```bash
OrbitingPlanetsProgressBar()
```



```bash
RotatingPetalsProgressBar()
```


```bash
RippleEffectProgressBar(
                modifier = Modifier.size(80.dp),
           
               
            )
```


```bash
BouncingBallProgressBar(
                modifier = Modifier.size(80.dp),
                size = 80.dp,
                color = Color.Magenta
            )
```


```bash
SpiralProgressBar(
                modifier = Modifier.size(80.dp),
                size = 80.dp,
                color = Color.Yellow
            )
```



## ✨✨ 2. BottomAppbars

## 🚀 BouncedBottomBar

 #### 2.1 --> Default BounceBottomBar

```bash
  var selectedIndex by remember { mutableStateOf(0) }

        Scaffold(
            bottomBar = {
                BounceBottomBar(
                    selectedIndex = selectedIndex,
                    onItemSelected = { selectedIndex = it },
                    items = defaultBounceBottomBarItemsWithSelectedIcons()
                )
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

```
https://github.com/user-attachments/assets/9151c767-0ce0-42b3-b300-a8bf37f289fe


#### 2.2 --> Customized BounceBottomBar
```bash
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
                    bounceScale = 2.0f,
                    selectedColor = Color.Magenta,
                    containerColor = Color.LightGray,
                    showLabels = false,
                    barHeight = 70.dp
                )
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





```
https://github.com/user-attachments/assets/fd868576-24eb-4802-964e-8d6e6ddd9a14



## 🚀 ExpandedBottomBar

#### 2.3 Default ExpandedBottomBar
```bash
var selectedIndex by remember { mutableStateOf(0) }

        Scaffold(
            bottomBar = {
                    ExpandableLabelBottomBar(
                    selectedIndex = selectedIndex,
                    onItemSelected = { selectedIndex = it },
                    items = defaultExpandableBottomBarItemsWithSelectedIcons(),
                )
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

```
https://github.com/user-attachments/assets/43300f92-2610-4672-9096-c2da62db84e3


#### 2.4 Customized ExpandedBottomBar
```bash
var selectedIndex by remember { mutableStateOf(0) }
        Scaffold(
            bottomBar = {
                    ExpandableLabelBottomBar(
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
                )
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

```
https://github.com/user-attachments/assets/23b146f4-ee96-407f-af10-cb51c5369246



## ✨✨ 3. TOPBAR

#### 🚀 3.1 AnimatedTopAppBar

```bash
val appBarState = rememberAnimatedSearchAppBarState()
var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            AnimatedTopAppBar(
                title = "Custom App",
                searchActive = appBarState.searchActive,
                onSearchActiveChange = { appBarState.searchActive = it },
                searchQuery = query,
                onSearchQueryChanged = { query = it },
                iconSize = 32.dp,
                menuIcon = Icons.Filled.Menu,
                searchIcon = Icons.Filled.Search,
                backIcon = Icons.Filled.ArrowBack,
                clearIcon = Icons.Filled.Clear,
                onMenuClick = {/* handle on menu click */ },
                iconColor = Color.White,
                containerColor = Color.Blue,
                contentColor = Color.White,
                searchFieldColor = Color.White,
                animationDuration = 500,
                titleStyle = MaterialTheme.typography.headlineSmall,
                searchFieldStyle = MaterialTheme.typography.titleMedium,
                elevation = 8.dp
            )
        }
    ) { _ -> }

```
https://github.com/user-attachments/assets/335b5b6e-2552-4cf2-83ef-21b2a8d2866b


#### 🚀 3.2 LiquidMotion TopAppBar

```bash
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
                titlePadding = PaddingValues(top = 8.dp),

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
```
https://github.com/user-attachments/assets/273e49cd-eafe-4b13-a852-69b1f05f1e59



## ✨✨ 4. Animated TextFields

#### 🚀 4.1 NeonTextField
```bash
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
```
https://github.com/user-attachments/assets/cfd69246-c9c2-4627-aa7d-3d7efc11ff9a



#### 🚀 4.2 ModernAnimatedTextField

```bash
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

```

#### 🚀 4.3 Bubble TextField
```bash
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

```
https://github.com/user-attachments/assets/6cef2551-13e6-4489-9b30-0126cd986880



#### 🚀 4.4 ScannerTextField
```bash
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

```
https://github.com/user-attachments/assets/1828ec31-2552-4ed3-8a24-7eddec951902



## ✨✨ 5. AnimatedButtons



https://github.com/user-attachments/assets/d77e7df9-4d85-4a36-9c53-712d38b2ef5f



```bash
ScaleButton(
            onClick = { /*TODO*/ },
            text = "Scale Button",
            textColor = Color.White,
            containerColor = Color.Blue,
            disabledContainerColor = Color.Gray.copy(alpha = 0.5f),
            elevation = ButtonDefaults.buttonElevation(),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(2.dp, Color.Red),
            icon = { Icon(imageVector = Icons.Default.Favorite,contentDescription = "Favorite") },
            // icon = null               if you not wanna use icon inside the Button
            animationDuration = 200,
            scaleFactor = 0.95f,
            enabled = true,
            interactionSource = remember { MutableInteractionSource() }
        )

```
```bash
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

```

```bash
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

```

```bash

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

```

```bash

RewardButton(
            onClick = { println("Reward claimed!") },
            text = "Get Bonus Coins",
            modifier = Modifier.height(70.dp).width(250.dp)
        )
```

```bash
BlinkButton(
            onClick = { /*TODO*/ },
            icon = Icons.Default.ThumbUp,
            modifier = modifier,
            buttonColor = Color(0xFF4CAF50),
            size = 56.dp,
            animationDuration = 600,
            glowColor = Color(0xFF2E7D32)
        )

```

```bash
 DoorLockButton(
            onClick = { /*TODO*/ },
            modifier = modifier,
            icon = Icons.Default.Lock,
            buttonColor = MaterialTheme.colorScheme.secondary,
            lockColor = Color.Green,
            size = 56.dp,
            animationDuration = 500,
        )

```

```bash
CoffeeCupButton(
            onClick = { /*TODO*/ },
            icon = Icons.Default.ShoppingCart,
            buttonColor = Color(0xFF8B4513),
            steamColor = Color.White.copy(alpha = 0.7f),
            size = 56.dp,
            animationDuration = 700,
            modifier = modifier,

        )
```


   
```bash
 HeartBeatButton(
            onClick = { /*TODO*/ },
            modifier = modifier,
            icon = Icons.Default.Favorite,
            buttonColor = Color.Red,
            pulseColor = Color.Red.copy(alpha = 0.4f),
            size = 56.dp,
            animationDuration = 600,
        )

```

## LICENSE

[MIT License](LICENSE)
