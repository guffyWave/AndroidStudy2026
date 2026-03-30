package com.example.cheezycodecomposestudy.screens


import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SimpleRecomposable(modifier: Modifier = Modifier) {
    val state = remember { mutableStateOf(0.0) }
    Log.d("GUFRAN", "SimpleRecomposable: >> Initial Composition ")
    Button(
        modifier = modifier.padding(25.dp),
        onClick = {
            state.value = Math.random()
        }) {
        Log.d("GUFRAN", "Logged at Composition & Recompostion  ")
        Text(text = "Random Number ${state.value}")
    }
}