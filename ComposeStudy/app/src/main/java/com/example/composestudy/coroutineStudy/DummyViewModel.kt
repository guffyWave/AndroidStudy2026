package com.example.composestudy.coroutineStudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DummyViewModel : ViewModel() {

    fun fetchData(): Unit {
        viewModelScope.launch {
            dowWork()
        }
    }

    fun dowWork() {
        for (fruit in listOf("Apple", "Banana", "Guava", "Mondak")) {
            // delay(1000L)
            println(fruit)
        }
    }

}