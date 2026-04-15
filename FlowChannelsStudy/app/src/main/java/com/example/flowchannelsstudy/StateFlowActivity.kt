package com.example.flowchannelsstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowchannelsstudy.viewmodels.CountrySearchViewModel
import com.example.flowchannelsstudy.viewmodels.SensorViewModel
import com.example.flowchannelsstudy.viewmodels.UserProfileViewModel


class StateFlowActivity : ComponentActivity() {

    private val counterViewModel: CounterViewModel by viewModels()
    private val userProfileViewModel: UserProfileViewModel by viewModels()
    private val countrySearchViewModel: CountrySearchViewModel by viewModels()

    private val sensorViewModel: SensorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_state_flow)

        setContent {
            Column(Modifier.padding(50.dp)) {
                CounterView(counterViewModel)
                ProfileScreen(userProfileViewModel)

                CountrySearchInputText(countrySearchViewModel)

                MagneticSensorView(sensorViewModel)
            }
        }
    }
}

@Composable
fun CounterView(counterViewModel: CounterViewModel) {

    //${counterViewModel.counter.value} -wrong way to use state flow, won’t recompose when the value changes
    // Compose wants you to collect the flow, not read it.

    var countState = counterViewModel.counter.collectAsState()

    Column(Modifier.padding(10.dp)) {
        Text(text = "Counter Value ${countState.value}")
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(onClick = {
                counterViewModel.increaseCounter()
            }) {
                Text(text = "Increase")
            }

            Button(onClick = {
                counterViewModel.decreaseCounter()
            }) {
                Text(text = "Decrease")
            }
        }
    }
}

@Composable
fun ProfileScreen(userProfileViewModel: UserProfileViewModel): Unit {
    val userProfileUIState = userProfileViewModel.userProfileUIState.collectAsState()

    Column(verticalArrangement = Arrangement.Center) {
        if (userProfileUIState.value.isLoading) {
            CircularProgressIndicator()
        }
        if (userProfileUIState.value.name != null) {
            Text(text = "${userProfileUIState.value.name}")
        }
        Button(onClick = {
            userProfileViewModel.loadProfile()
        }) {
            Text(text = "Load Profile")
        }
    }

}

@Composable
fun CountrySearchInputText(countrySearchViewModel: CountrySearchViewModel): Unit {

    val queryTextInputState = countrySearchViewModel.query.collectAsState()

    TextField(value = queryTextInputState.value, onValueChange = {
        countrySearchViewModel.updateQuery(it)
    })
}


@Composable
fun MagneticSensorView(sensorViewModel: SensorViewModel) {
    var sensorEventState = sensorViewModel.sensorEvent.collectAsState()

    Column(Modifier.padding(10.dp)) {
        Text(text = "Magnetic Sensor X : ${sensorEventState.value.x}", fontSize = 20.sp)
        Text(text = "Magnetic Sensor Y : ${sensorEventState.value.y}", fontSize = 20.sp)
        Text(text = "Magnetic Sensor Z : ${sensorEventState.value.z}", fontSize = 20.sp)

    }
}