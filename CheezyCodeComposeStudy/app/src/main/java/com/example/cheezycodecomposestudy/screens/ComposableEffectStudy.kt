package com.example.cheezycodecomposestudy.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun SimpleComposable(modifier: Modifier = Modifier) {
    val notificationCount = remember { mutableStateOf(0) }
    val userName = remember { mutableStateOf("") }


    ///registering listeners , starting animation
    DisposableEffect(Unit) {
        Log.d("SimpleComposable", " DisposableEffect --->> on Mount  --- >")

        onDispose {
            Log.d("SimpleComposable", "XXX-- onDispose ---XXX  Left Compostion  ")
        }
    }

    //every time of before recompose happens - e.g loging analytics
    SideEffect {
        Log.d("SimpleComposable", "Side Effect --->>> ${notificationCount.value} ")
    }

    LaunchedEffect(notificationCount.value) {
        Log.d("SimpleComposable", "LaunchedEffect --->>>  ${notificationCount.value}")
    }

    ///
    LaunchedEffect(Unit) {
        Log.d("SimpleComposable", "LaunchedEffect  Unit --->>>  ${notificationCount.value}")

    }


    Column(modifier.padding(50.dp)) {
        Text(text = "Your count is ${notificationCount.value} notifications")
        Button(onClick = {
            //onButtonClicked()
            notificationCount.value++
        }) {
            Text(text = "Click ME")
        }
    }

}