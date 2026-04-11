package com.example.cheezycodecomposestudy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Preview
@Composable
private fun AppPreview() {
    RectangularArea()
}

@Composable
fun RectangularArea() {
    /// var lengthState = remember { mutableStateOf(1) }
    var lengthState = produceState(0) {
        for (length in 1..100) {
            delay(500)
            value = length
        }
    }
    //var widthState = remember { mutableStateOf(0) }
    var widthState = produceState(0) {
        for (width in 1..100) {
            delay(50)
            value = width
        }
    }

    //Why can't I just write val area = length.value * width.value?"
    //derivedStateOf is a performance optimization
    val areaState = remember { derivedStateOf { lengthState.value * widthState.value } }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(50.dp)
            .fillMaxWidth()
    ) {

        Text(
            text = "Width : ${widthState.value}",
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Length : ${lengthState.value}",
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Area is ${areaState.value}",
            fontSize = 30.sp,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth()
        )
    }

}