package com.example.flowchannelsstudy.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

data class Location(val lat: Double, val long: Double)

class LocationViewModel : ViewModel() {
    private val _locationEvent = MutableSharedFlow<Location>(replay = 1)

    //replay = 1 will make sure new subscriber will get the last location value
    val locationEvent = _locationEvent.asSharedFlow()

    init {
        startLocationUpdates()
    }

    fun startLocationUpdates(): Unit {
        viewModelScope.launch {
            var lat = 28.342445222
            //simulated location updates
            while (true) {
                delay(1000)
                lat += Random.nextFloat() * 0.10
                _locationEvent.emit(Location(lat, 72.000))
            }
        }
    }

}