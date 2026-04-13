package com.example.composethemestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.composethemestudy.ui.theme.ComposeThemeStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}


///creating button to toggle light to dark
@Composable
fun App() {
    var theme = remember { mutableStateOf(false) }

    val context =
        LocalContext.current //  Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show().
    val configuration =
        LocalConfiguration.current // Provides the Configuration object, which contains details about the device's current state. // Checking screen orientation, screen width/height in DP, or whether the device is in "Night Mode."
    val view = LocalView.current

    //see below composable
    val localLifeCycleOwner = LocalLifecycleOwner.current





    ComposeThemeStudyTheme(theme.value) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            Text(
                text = "Gufran Khurshid Bismillah ",
                style = MaterialTheme.typography.headlineLarge
            )
            Button(onClick = {
                theme.value = !theme.value
            }) {
                Text(text = "Toggle Theme")
            }
        }
    }
}


@Composable
fun LifecycleObserverExample() {
    // 1. Get the current LifecycleOwner
    val lifecycleOwner = LocalLifecycleOwner.current

    // 2. Use DisposableEffect to manage the observer's lifecycle
    DisposableEffect(lifecycleOwner) {
        // Create an observer
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    println("App is in the foreground - Start heavy tasks")
                }

                Lifecycle.Event.ON_STOP -> {
                    println("App is in the background - Pause heavy tasks")
                }

                else -> {}
            }
        }

        // 3. Add the observer
        lifecycleOwner.lifecycle.addObserver(observer)

        // 4. Crucial: Remove the observer when the Composable leaves the Composition
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Text("I am aware of the Activity Lifecycle!")
}