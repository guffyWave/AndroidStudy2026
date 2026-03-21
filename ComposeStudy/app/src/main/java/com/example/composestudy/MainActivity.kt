package com.example.composestudy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composestudy.coroutineStudy.LifeCycleStudyActivity
import com.example.composestudy.ui.theme.ComposeStudyTheme

class MainActivity : ComponentActivity() {
    lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            ComposeStudyTheme {


//                Scaffold(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(20.dp)
//                ) { innerPadding ->
//                    FancyButton(text = "Hello")
//                    GoogleButton(onClicked = { Log.d("Google Check ", "onCreate: ") })
//                }

                navHostController = rememberNavController()
                AppNavHost(navHostController = navHostController)
            }
        }


        val intent = Intent(this, LifeCycleStudyActivity::class.java)
        intent.putExtra("EXTRA_DATA", "Hello from MainActivity!")
        startActivity(intent)

    }
}


@Composable
fun TextStudy(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .width(400.dp)
            .height(200.dp)
    ) {
        Text(
            text = "Hello World ",
            modifier = Modifier
                .background(color = Color.Green)
                .padding(16.dp),
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Italic,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
    }
}

@Preview
@Composable
private fun TextStudyPreview() {
    ComposeStudyTheme {
        TextStudy()
    }
}

@Composable
fun FancyButton(text: String) {
    var myValue by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    //will be triggered when component renders for the first time
    Log.d("FancyButton ", "Recomposition ")

    Button(onClick = { myValue = !myValue }) {
        Text(text = "$myValue", style = MaterialTheme.typography.bodyLarge)

        //will be recomposed when button is clicked
        Log.d("FancyButton ", "Recomposition - Button Content Lambda ")
    }
}

@Composable
fun ColumnScope.ColumnItem(weight: Float, color: Color = MaterialTheme.colorScheme.error): Unit {
    Surface(
        modifier = Modifier
            .width(200.dp)
            .weight(weight), color = color
    ) {}
}

//fun RowScope.abc(): Unit {
//}

@Composable
fun ColumScopeStudy(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ColumnItem(3f, color = MaterialTheme.colorScheme.surface)
        ColumnItem(1f)
    }
}

@Preview
@Composable
private fun ColumnScopePreview() {
    ComposeStudyTheme {
        ColumScopeStudy()
    }
}

@Composable
fun SurfaceWeightStudy(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Surface(
            modifier = Modifier
                .width(200.dp)
                .weight(3f),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {

        }
        Surface(
            modifier = Modifier
                .width(200.dp)
                .weight(1f),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {}
    }
}

@Preview
@Composable
private fun SurfaceWeightStudyPreview() {
    ComposeStudyTheme {
        SurfaceWeightStudy()
    }
}

@Composable
fun SurfaceStudy(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Surface(
            modifier = Modifier
                .width(200.dp)
                .height(100.dp),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {

        }
        Surface(
            modifier = Modifier
                .width(200.dp)
                .height(100.dp),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {}
        Surface(
            modifier = Modifier
                .width(200.dp)
                .height(100.dp),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {}
    }
}

@Preview
@Composable
private fun SurfaceStudyPreview() {
    ComposeStudyTheme {
        SurfaceStudy()
    }
}

@Composable
fun LayoutColumnStudy(modifier: Modifier = Modifier) {
    Column {
        Text(text = "Bismillah Hirrahma Nirrahim", style = MaterialTheme.typography.displayLarge)
        Text(text = "Apple is red", style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun LayoutRowStudy(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color.LightGray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Bismillah Hirrahma Nirrahim", style = MaterialTheme.typography.displayLarge
        )
        Text(text = "Apple is red", style = MaterialTheme.typography.labelLarge)

    }
}

@Composable
fun LayoutBoxStudy(modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .background(color = Color.Cyan)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "Apple is red", style = MaterialTheme.typography.bodyLarge)
        }

        Box(
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .background(color = Color.Red),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(text = "Hello World ", style = MaterialTheme.typography.displayLarge)
        }

    }

}

@Composable
@Preview
fun FancyButtonPreview(modifier: Modifier = Modifier) {
    ComposeStudyTheme {
        FancyButton("Bismillah")
    }
}

@Composable
@Preview
fun LayoutColumnStudyPreview(modifier: Modifier = Modifier) {
    ComposeStudyTheme {
        LayoutColumnStudy()
    }
}

@Composable
@Preview
fun LayoutRowStudyPreview(modifier: Modifier = Modifier) {
    ComposeStudyTheme {
        LayoutRowStudy()
    }
}

@Composable
@Preview
fun LayoutBoxStudyPreview(modifier: Modifier = Modifier) {
    ComposeStudyTheme {
        LayoutBoxStudy()
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ComposeStudyTheme {
//        Greeting("Android")
//    }
//}