package com.example.cheezycodecomposestudy

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Preview
@Composable
private fun AppPreview() {
    App()
}

@Composable
fun App(modifier: Modifier = Modifier) {
    var count = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        delay(5000)
        count.value = 786
    }

    Column(modifier.padding(50.dp)) {
        Counter(count.value)
    }
}

@Composable
fun Counter(currentCount: Int) {
    val updatingCurrentCount = rememberUpdatedState(currentCount)
    LaunchedEffect(Unit) {
        delay(10000)
        Log.d("CounterLaunchedEffect", "currentCount: ${currentCount}")
        Log.d("CounterLaunchedEffect", "updatingCurrentCount: ${updatingCurrentCount.value}")
    }
    Text(text = currentCount.toString())
}

fun executeSanta(): Unit {
    Log.d("REM_UPDATED_STATE", "executing Santa ... ")
}

fun executeBanta(): Unit {
    Log.d("REM_UPDATED_STATE", "executing Bantaa Burrah  ... ")
}

@Preview
@Composable
private fun AnotherAppPreview() {
    AnotherApp()
}

@Composable
fun AnotherApp(modifier: Modifier = Modifier) {
    var action = remember { mutableStateOf(::executeSanta) }

    Column(Modifier.padding(50.dp)) {
        Button(onClick = {
            //Note to see the effect call the button within 10 seconds
            Log.d("REM_UPDATED_STATE", "Button clicked -  ")
            action.value = ::executeBanta
        }) {
            Text(text = "Click to Change Action")
        }
    }

    SplashScreen(action.value)
}

//when the splash screen completes execute the function based on what was provided
@Composable
fun SplashScreen(onTimeOut: () -> Unit) {
    val currentOnTimeOut by rememberUpdatedState(onTimeOut)

    //LaunchedEffect(key): Use this when you want to RESTART the process (like a fresh API call or resetting a timer) whenever the data changes.
    // rememberUpdatedState + LaunchedEffect(Unit): Use this when you want the process to CONTINUE uninterrupted, but use the latest data when it finally finishes.
    LaunchedEffect(Unit) {
        delay(10000)
        currentOnTimeOut()
    }
}

//@Preview
//@Composable
//private fun ToastAppPreview() {
//    ToastApp(Modifier.padding(100.dp))
//}
//
//@Composable
//fun ToastApp(modifier: Modifier = Modifier) {
//    val userMessageState = remember { mutableStateOf("First Message ") }
//    Column(modifier) {
//        Button(onClick = {
//            userMessageState.value = "Second Message"
//        }) {
//            Text(text = "Click to Show Delayed Toast ")
//        }
//        DelayedToastAndMessage(userMessageState.value)
//    }
//
//
//}
//
//@Composable
//fun DelayedToastAndMessage(message: String) {
//    val context = LocalContext.current
//    val updatedMessageState = rememberUpdatedState(message)
//    LaunchedEffect(Unit) {
//        delay(5000)
//        Log.d("DelayedToastAndMessage", "DelayedToastAndMessage:${updatedMessageState.value} ")
//        //Toast.makeText(context, message, Toast.LENGTH_SHORT)
//    }
//    Text(text = updatedMessageState.value)
//}
