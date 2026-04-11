package com.example.cheezycodecomposestudy

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun AppPreview() {
    App()
}

@Composable
fun App() {
    var counter = remember { mutableStateOf(0) }

    DisposableEffect(Unit) {
        Log.d("DISPOSAL_EFFECT_APP", "----DisposableEffect Effect Started  ")

        onDispose {
            //when composable gets outof screen(disposed) ,this codes get executed
            Log.d("DISPOSAL_EFFECT_APP", "----onDispose")
        }
    }


    DisposableEffect(counter.value) { // NOTE - NOT  counter
        Log.d("DISPOSAL_EFFECT_APP", "----counter DisposableEffect   Effect Started   ")

        onDispose {
            Log.d("DISPOSAL_EFFECT_APP", "---- counter onDispose")
        }
    }


    Column(Modifier.padding(100.dp)) {
        Text(text = "This is app component ")

        Button(onClick = {
            counter.value += 1
        }) {
            Text(text = "Change State")
        }
    }
}