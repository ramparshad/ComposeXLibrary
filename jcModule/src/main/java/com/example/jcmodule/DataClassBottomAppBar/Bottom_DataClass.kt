package com.example.jcmodule.DataClassBottomAppBar

import androidx.compose.ui.graphics.vector.ImageVector

data class BounceBottomBarItem(
    val icon: ImageVector,
    val label: String,
    val selectedIcon: ImageVector? = null
)

data class ExpandableBottomBarItem(
    val icon: ImageVector,
    val label: String,
    val selectedIcon: ImageVector? = null
)
