package com.example.composestudy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MutableStateListOfDemo() {

    // var fruits = remember { mutableStateListOf("Apple", "Banana") }
    var fruits = remember { mutableListOf("Apple", "Banana") }
    var fruitName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        TextField(
            value = fruitName,
            onValueChange = { fruitName = it },
            label = { Text("Add fruit") }
        )

        Button(onClick = {
            if (fruitName.isNotBlank()) {
                fruits.add(fruitName)
                fruitName = ""
            }
        }) {
            Text(text = "Add Fruit")
        }



        Text(text = "Fruit List")

        LazyColumn {
            items(fruits) { fruit ->
                Text(text = fruit)
            }
        }

        Button(onClick = {
            fruits.removeAt(fruits.size - 1)
        }) {
            Text(text = "Remove")
        }
    }

}

@Composable
@Preview
private fun MutableStateListOfDemoPreview() {
    MutableStateListOfDemo()
}