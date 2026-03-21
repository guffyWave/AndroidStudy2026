package com.example.composestudy.flowstudy_sharedflow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ComposableOne() {
    Button(onClick = { EventMaster.fireEvent("Hello from ComposableOne!") }) {
        Text("Send Event")
    }
}

@Composable
fun ComposableTwo() {
    var message by remember { mutableStateOf("Waiting for event...") }
    // Collect the event once per composition
    LaunchedEffect(Unit) {
        EventMaster.eventFlow.collect { msg ->
            message = msg
        }
    }
    Text("ComposableTwo received: $message")
}

@Composable
fun ComposableThree() {
    var message by remember { mutableStateOf("Waiting for event...") }
    LaunchedEffect(Unit) {
        EventMaster.eventFlow.collect { msg ->
            message = msg
        }
    }
    Text("ComposableThree received: $message")
}


@Composable
fun DemoSharedFlowScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        ComposableOne()
        Spacer(modifier = Modifier.height(16.dp))
        ComposableTwo()
        Spacer(modifier = Modifier.height(8.dp))
        ComposableThree()
    }
}

@Preview
@Composable
private fun DemoSharedFlowScreenPreview() {
    DemoSharedFlowScreen()
}