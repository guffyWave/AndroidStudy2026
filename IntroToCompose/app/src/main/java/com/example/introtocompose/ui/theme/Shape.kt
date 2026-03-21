package com.example.introtocompose.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// defining a set of common UI shapes (small, medium, and large) with specific corner radii, which can then be consistently applied throughout a Jetpack Compose application to maintain a unified design
// calling the constructor of a Shapes class
val Shapes = Shapes(
    //holds definitions for different shape styles used in the UI
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)