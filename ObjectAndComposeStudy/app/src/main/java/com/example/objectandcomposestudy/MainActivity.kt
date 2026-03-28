package com.example.objectandcomposestudy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.objectandcomposestudy.helpers.AnalyticsLogger
import com.example.objectandcomposestudy.helpers.AnimalHelper
import com.example.objectandcomposestudy.helpers.AquariumHelper
import com.example.objectandcomposestudy.helpers.CarEngine
import com.example.objectandcomposestudy.helpers.LoginState
import com.example.objectandcomposestudy.helpers.validateLogin
import com.example.objectandcomposestudy.shared.SessionManager
import com.example.objectandcomposestudy.ui.theme.ObjectAndComposeStudyTheme
import java.util.Date

const val TAG = "GUFRAN"


//object singleton cas be used XXUtility // Helper/ Repository // AppConstants //XXXManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Log.d("GUFRAN", "onCreate: -- ")
        usingUtilities()

        val height = 200.0f
        val width = 300.0f
        val aspectRatio = ImageDimensionHelper.getAspectRatio(width, height)

        Log.d(TAG, "AspectRatio - ${aspectRatio}")

        val imageArea = ImageDimensionHelper.calculateImageArea(width.toInt(), height.toInt())
        Log.d(TAG, "onCreate: Image Area -${imageArea}")

        val weightAverage = AnimalHelper.getAnimalAverageWeight(listOf(82, 91, 4, 12, 92, 38))

        Log.d(TAG, "onCreate: weightAverage : ${weightAverage}")

        Log.d(TAG, "onCreate: Persistence Level - ${PersistenceManager.getPersistenceLevel()} ")

        AnalyticsLogger.logMessage("Bismillah Hirrahman Nirrahim")

        CarEngine.getDefaultEngine()

        val loginResult = validateLogin("gufran", "12")
        when (loginResult) {
            is LoginState.Idle -> println("Value is Idle")
            is LoginState.Error -> println("Value is ${loginResult.message} ")
            is LoginState.Success -> println("Value is ${loginResult.username} ")
            is LoginState.Loading -> println("Loading ")
            else -> println("NA ..  ")
        }
        
        setContent {
            ObjectAndComposeStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    fun usingUtilities() {
        val fancyDate = DateUtils.convertDateToFriendlyDate(Date())

        //  val dateUtil = DateUtils() // can't instantiate object

        Log.d("GUFRAN", "usingUtilities: -- " + fancyDate)
        //  AnimalsRepository.zooAnimalsList

        AquariumHelper.getMarineAnimals().forEach {
            println(it)
        }

    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column() {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(
            onClick = {
                Log.d(TAG, "Greeting: Button Clicked")

                val defaultValue = AppConstants.TIMEOUT
                Log.d(TAG, "usingUtilities ${defaultValue}")

                Log.d(TAG, "Checking Seesion Info , Auth Token ${SessionManager.authToken}")
                Log.d(TAG, "Session Is LoggedIn  --  ${SessionManager.isLoggedIn()}")
                SessionManager.authToken = "anshsaj"
                Log.d(TAG, "Checking Seesion Info , Auth Token ${SessionManager.authToken}")
                Log.d(TAG, "Session Is LoggedIn  --  ${SessionManager.isLoggedIn()}")

            }, modifier = Modifier
                .height(50.dp)
                .width(120.dp)
        ) { Text(text = "Click Me ") }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ObjectAndComposeStudyTheme {
        Greeting("Android")
    }
}