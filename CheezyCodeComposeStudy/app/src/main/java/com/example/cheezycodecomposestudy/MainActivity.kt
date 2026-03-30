package com.example.cheezycodecomposestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview

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
            SayCheezy("CHeezy Codes")
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