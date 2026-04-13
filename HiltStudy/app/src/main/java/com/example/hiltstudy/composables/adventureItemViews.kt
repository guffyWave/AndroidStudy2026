package com.example.hiltstudy.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp


@Composable
//fun AdventureViewItem(genericAdventureInfo: GenericAdventureInfo) { // con't directly inject
fun AdventureViewItem() {
//    Log.d(
//        TAG,
//        "AdventureViewItem Composable  genericAdventureInfo --- ${genericAdventureInfo.getAdventureLocation()}"
//    )
    Text(text = "Adventure View Item", fontSize = 26.sp)
}

