package com.example.introtocompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntroToComposeTheme {
                //Scaffold(modifier = Modifier.fillMaxSize(),) { innerPadding ->

//                Surface(
//                    color = MaterialTheme.colorScheme.primary,
//                    modifier = Modifier
//                        .padding(vertical = 100.dp)
//                        .fillMaxWidth(0.4f)
//                ) {
//                    Greeting(
//                        name = "Android",
//                    )
//                }

                MyApp();

                // }
            }
        }
    }
}


@Composable
fun MyApp() {

    var moneyC = remember { mutableStateOf(0) }

    Surface(
        color = Color(0xFF546E7A),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        //Text(text = "Hello")
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$ ${moneyC.value}", style = TextStyle(
                    color = Color.White,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Spacer(modifier = Modifier.height(60.dp))
            CreateCircle()
            Spacer(modifier = Modifier.height(20.dp))
            CreateCircleHoisted(moneyC.value) { newValue ->
                moneyC.value = newValue
            }

            if (moneyC.value > 25) {
                Text(text = "Lots of money")
            }
        }
    }

}

@Preview
@Composable
fun CreateCircle() {
    // Add the import for getValue and setValue
    var moneyCounter by remember { mutableStateOf(0) }

    var moneyC = remember { mutableStateOf(0) }

    Card(
        modifier = Modifier
            .padding(3.dp)
            // .size(45.dp, 12.dp),
            .size(60.dp)
            .clickable {
                moneyCounter += 1;Log.d("Gufran", "Counter Value - " + moneyCounter)
                moneyC.value = moneyC.value + 1;
            },
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize() // Add this line
            //.background(Color.Yellow), // gives background color for identification
            ,
            contentAlignment = Alignment.Center
        ) {
            //Text(text = "Tap $moneyCounter", modifier = Modifier)
            Text(text = "Tap ${moneyC.value}", modifier = Modifier)

        }
    }
}


@Composable
fun CreateCircleHoisted(moneyCounter: Int = 0, updateMoneyCounter: (Int) -> Unit) {

    Card(
        modifier = Modifier
            .padding(3.dp)
            // .size(45.dp, 12.dp),
            .size(60.dp)
            .clickable {
                updateMoneyCounter(moneyCounter + 1)
            },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Tap Hoisted ",
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }
}


//@Preview
@Composable
fun ShowAge(age: Int = 12) { // if preview then a default param has to be passed
    Text(text = age.toString())
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


//@Preview(name = "Me", showBackground = true)
//@Preview(name="Me", group = "FirstGroup", showBackground = false)
//@Preview
//@Composable
//fun GreetingPreview() {
//    IntroToComposeTheme(darkTheme = true) {
//        Greeting("Android")
//        ShowAge()
//
//    }
//}

@Preview
@Composable
fun AppPreview() {
    MyApp()
}