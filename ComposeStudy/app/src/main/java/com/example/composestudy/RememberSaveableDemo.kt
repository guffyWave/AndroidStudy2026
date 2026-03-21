package com.example.composestudy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun RememberSaveableDemo(modifier: Modifier = Modifier) {

    var rememberedCounter by remember { mutableStateOf(0) }
    var rememberedSaveableCounter by rememberSaveable { mutableStateOf(0) }

    Column(modifier = Modifier.padding(80.dp)) {
        Text(text = "Value Remembered Counter : $rememberedCounter")
        Text(text = "Value Remembered Saveable Counter : $rememberedSaveableCounter")

        Button(onClick = {
            rememberedCounter++
            rememberedSaveableCounter++
        }) {
            Text(text = "Increment")
        }
    }

}

@Composable
@Preview
private fun RememberSaveableDemoPreview() {
    RememberSaveableDemo()
}