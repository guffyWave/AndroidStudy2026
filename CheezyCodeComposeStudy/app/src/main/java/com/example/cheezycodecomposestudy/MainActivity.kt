package com.example.cheezycodecomposestudy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val composeView: ComposeView by lazy {
        findViewById(R.id.composeContainer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //     setContentView(R.layout.activity_main)

        setContent {
            //Text(text = "Bismmillha Hirrahman Nirrahim ")
            // SayCheezy("CHeezy Codes")

            ListComposables()
        }

        //    composeView.setContent { SayCheezy("From Cmpose View in XML Layout ") }
    }
}


@Composable
fun SayCheezy(name: String) {
    Text(text = "Hello $name")
}


//@Preview(
//    showBackground = true, name = "Hello Widget",
////    showSystemUi = true
//    widthDp = 200,
//    heightDp = 200
//)
@Composable
private fun SayCheezyPreview() {
    SayCheezy(name = "Gufran")
}


@Composable
@Preview
private fun RepeatedGreetingPreview() {
    Text(text = "Hello Gufran")
}

fun getFruitsName(): List<String> {
    return listOf<String>("Guava", "Mango", "Lichi", "")
}


@Composable
fun ListComposables() {
    val categoryState = remember { mutableStateOf(emptyList<String>()) }

    //will be executed only once
    LaunchedEffect(Unit) {
        categoryState.value = fetchCategories()
    }

    SideEffect {
    }

    DisposableEffect(categoryState.value) {
        onDispose {
        }
    }

    LazyColumn() {
        items(categoryState.value) { item ->
            Text(text = item)
        }
    }
}

fun fetchCategories(): List<String> {
    ///assuming network calls
    return listOf("One", "Two", "Three")
}

@Preview
@Composable
private fun LaunchEffectComposablePreview() {
    LaunchEffectComposable()
}

@Composable
fun LaunchEffectComposable(modifier: Modifier = Modifier.padding(50.dp)) {
    var counter = remember { mutableStateOf(0) }
    var scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        Log.d("LaunchEffectComposable", "Started ...  ")
        try {
            for (i in 1..10) {
                delay(1000)
                counter.value++
            }
        } catch (e: Exception) {
            Log.d("LaunchEffectComposable", e.message.toString())
        }

    }
    var text = "Counter is running ${counter.value}"
    if (counter.value == 10) {
        text = "Counter stopped "
    }
    Text(text = text, modifier)
}

@Preview
@Composable
private fun CoroutineScopeStudyComposablePreview() {
    CoroutineScopeStudyComposable()
}

@Composable
fun CoroutineScopeStudyComposable(modifier: Modifier = Modifier.padding(50.dp)) {
    var counter = remember { mutableStateOf(0) }
    var scope = rememberCoroutineScope()


    Column(modifier) {
        Text(text = "Counter is ${counter.value}")
        Button(onClick = {
            scope.launch {
                for (i in 1..10) {
                    counter.value++
                    delay(1000)
                }
            }
        }) {
            Text(text = "Start")
        }
    }

}
