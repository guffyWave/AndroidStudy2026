package com.example.flowchannelsstudy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

data class MagneticSensorEvent(val x: Double, val y: Double, val z: Double)

class SensorViewModel : ViewModel() {
    private val _sensorEvent =
        MutableStateFlow<MagneticSensorEvent>(MagneticSensorEvent(0.0, 0.0, 0.0))
    val sensorEvent = _sensorEvent.asStateFlow()

    init {
        onSensorStart()
    }

    private fun onSensorStart() {
        viewModelScope.launch {
            while (true) {
                delay(300)
                //simulated sensor event
                _sensorEvent.value =
                    MagneticSensorEvent(
                        Random.nextDouble(),
                        Random.nextDouble(),
                        Random.nextDouble()
                    )
            }
        }
    }
}
