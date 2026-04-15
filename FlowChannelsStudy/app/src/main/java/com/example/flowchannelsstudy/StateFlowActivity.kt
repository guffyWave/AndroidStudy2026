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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class StateFlowActivity : ComponentActivity() {

    private val counterViewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_state_flow)

        setContent {
            CounterView(counterViewModel)
        }
    }
}

@Composable
fun CounterView(counterViewModel: CounterViewModel) {

    //${counterViewModel.counter.value} -wrong way to use state flow, won’t recompose when the value changes
    // Compose wants you to collect the flow, not read it.

    var countState = counterViewModel.counter.collectAsState()


    Column(Modifier.padding(50.dp)) {
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