package com.example.cheezycodecomposestudy

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


//Composables can hold UI state.
@Composable
fun Counter() {
    var count = remember { mutableStateOf(0) }
    Button(onClick = { count.value++ }) {
        Text("Clicked ${count.value} times")
    }
}

