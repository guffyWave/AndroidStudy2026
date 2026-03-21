package com.example.composestudy.container

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun StickyHeaderContainer(modifier: Modifier = Modifier) {
    val groupedData = mapOf(
        "A" to listOf("Amir", "Amit", "Ananya"),
        "B" to listOf("Bilal", "Bobby", "Bina"),
        "C" to listOf("Charlie", "Chirag", "Catherine"),
        "D" to listOf("Danish", "Deepak", "Divya")
    )


    LazyColumn {
        groupedData?.forEach { initial, names ->
            stickyHeader {
                Text(text = "Section $initial")
            }

            items(items = names) { name ->
                Text(modifier = Modifier.height(100.dp), text = name)
            }
        }

    }
}

@Preview
@Composable
private fun StickyHeaderContainerPreview() {
    StickyHeaderContainer()
}