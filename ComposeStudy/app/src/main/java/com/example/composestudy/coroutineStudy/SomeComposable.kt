package com.example.composestudy.coroutineStudy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    var message by remember { mutableStateOf("") }

    Button(
        onClick = {
            coroutineScope.launch {
                message = "Loading"
                delay(5000L)
                message = "Login Successful!"
            }

        }) {
        Text(text = "Login ")
    }

    Spacer(modifier = Modifier.padding(32.dp))

    Text(text = "Message ${message} ")
}

@Preview
@Composable
private fun LoginScreenPreview() {
    Column(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
        LoginScreen()
    }

}