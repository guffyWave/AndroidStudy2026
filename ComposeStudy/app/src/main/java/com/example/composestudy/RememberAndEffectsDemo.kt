package com.example.composestudy

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RememberAndEffectsDemo(modifier: Modifier = Modifier) {

    var normalCounter = 0
    var rememberedCounter by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(50.dp)) {
        Text(text = "Value Normal Counter : $normalCounter")
        Text(text = "Value Remembered Counter : $rememberedCounter")

        Button(onClick = {
            normalCounter++
            rememberedCounter++
        }) {
            Text(text = "Increment")
        }
    }

    LaunchedEffect(Unit) {
        Log.d(
            "RememberDemo",
            "LaunchedEffect: Unit = "
        )
    }

    // Log whenever rememberedCounter changes
    LaunchedEffect(rememberedCounter) {
        Log.d(
            "RememberDemo",
            "LaunchedEffect: rememberedCounter = ${rememberedCounter} and normalCounter=${normalCounter}"
        )
    }


    // Log after every successful recomposition
    SideEffect {
        Log.d(
            "RememberDemo",
            "SideEffect: rememberedCounter = ${rememberedCounter} and normalCounter=${normalCounter}"
        )
    }

    // Log and clean up, runs when this Composable enters and leaves the composition
    DisposableEffect(Unit) {
        Log.d("RememberDemo", "DisposableEffect: Composable ENTERED composition")
        onDispose {
            Log.d("RememberDemo", "DisposableEffect: Composable LEFT composition")
        }
    }


}


@Preview
@Composable
private fun RememberDemoPreview() {
    RememberAndEffectsDemo()
}