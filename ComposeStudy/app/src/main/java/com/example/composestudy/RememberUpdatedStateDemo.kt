package com.example.composestudy


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun RememberUpdatedStateDemo(modifier: Modifier = Modifier) {
    var message by remember { mutableStateOf("Initial message") }
    var show by remember { mutableStateOf(false) }

    // This captures the value at composition time and DOES NOT update!
    val rememberedMessage = remember(message) { message }

    // This always reflects the latest value -- even after recompositions!
    val latestMessage by rememberUpdatedState(message)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        TextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Type a message") }
        )
        Button(onClick = { show = true }) {
            Text("Show messages after delay")
        }
        if (show) {
            LaunchedEffect(Unit) {
                delay(3000)
                println("rememberedMessage after delay: $rememberedMessage")
                println("latestMessage after delay: $latestMessage")
                show = false
            }
            Text("Wait for 3 seconds then check your log")
        }
    }
}


@Composable
@Preview
private fun RememberUpdatedStateDemoPreview() {
    RememberUpdatedStateDemo()
}