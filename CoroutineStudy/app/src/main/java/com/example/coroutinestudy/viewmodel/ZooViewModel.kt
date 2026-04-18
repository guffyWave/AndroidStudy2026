package com.example.coroutinestudy.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ZooViewModel : ViewModel() {

    var zooAnimalsState = MutableStateFlow<List<String>>(emptyList())

    init {
        populateReptileAnimals()
    }

    private fun populateReptileAnimals() {
        viewModelScope.launch(Dispatchers.Main) {
            val reptiles = withContext(Dispatchers.IO) {
                fetchReptiles()
            }
            Log.d("ZooViewModel", "Reptile fetched: $reptiles")
            zooAnimalsState.value += reptiles //adding reptiles to zooAnimals
        }
    }

    suspend fun fetchZooAmphibians(): List<String> {
        val deferredFetchAmphibians = viewModelScope.async(Dispatchers.IO) {
            fetchAmphibians()
        }

        val amphibians = deferredFetchAmphibians.await()
        zooAnimalsState.value += amphibians
        return amphibians
    }

    private suspend fun fetchReptiles(): List<String> {
        // delay(2500)
        return listOf("Snakes", "Lizard", "Iguana", "Crocodile", "Turtle")
    }

    private suspend fun fetchAmphibians(): List<String> {
        return listOf("Frog", "Salamander", "Toad")

    }


}