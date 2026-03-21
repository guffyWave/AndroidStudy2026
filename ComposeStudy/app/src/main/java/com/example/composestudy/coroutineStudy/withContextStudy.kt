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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


fun main() = runBlocking {
    println("Running on thread: ${Thread.currentThread().name}")

    withContext(Dispatchers.IO) {
        println("Switched to thread: ${Thread.currentThread().name}")
        delay(1000L)
        println("Still on IO thread")
    }

    println("Back to thread: ${Thread.currentThread().name}")

}


@Composable
fun DummyScreen(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    Button(
        onClick = {
            coroutineScope.launch {
                println("Launch Called --> running on thread: ${Thread.currentThread().name}")
                withContext(Dispatchers.IO) {
                    printNumber()
                }
                println("Back to thread: ${Thread.currentThread().name}")
            }

        }) {
        Text(text = "Login ")
    }
}

@Preview
@Composable
private fun DummyScreenPreview() {
    Column(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
        DummyScreen()
    }

}


suspend fun printNumber(): Unit {
    for (number in 1..5) {
        delay(1000)
        println(" Running on thread: ${Thread.currentThread().name} --- Number ${number}")
    }
}


fun someTestFunction(): Unit {

//    viewModelScope.launch {
//        val user = withContext(Dispatchers.IO) {
//            repository.getUserFromDatabase() // suspend function
//        }
//        // Back on Main thread
//        _uiState.value = user
//    }
}