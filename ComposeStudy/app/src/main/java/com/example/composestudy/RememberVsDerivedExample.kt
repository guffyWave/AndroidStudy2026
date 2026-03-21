package com.example.composestudy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RememberVsDerivedExample() {
    var input by remember { mutableStateOf("") }
    /// var reverseStringRemembered by remember { input.reversed() } wrong as --- initial value of input (which is ""), and never recomputes it — even when input changes and  changing state (input) inside a remember block that should run only once - that’s invalid, --- hence the 'by' keyword not allowed'

    // Using remember to cache a computed value (but doesn't track input changes):
    var reversedRemember = remember { input.reversed() }

    // Using derivedStateOf to automatically recompute on input change:
    val reversedDerived by remember { derivedStateOf { input.reversed() } }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = input, onValueChange = { input = it })
        Text("Reversed with remember: $reversedRemember")
        Text("Reversed with derivedStateOf: $reversedDerived")
    }
}

@Composable
@Preview
private fun RememberVsDerivedExamplePreview() {
    RememberVsDerivedExample()
}