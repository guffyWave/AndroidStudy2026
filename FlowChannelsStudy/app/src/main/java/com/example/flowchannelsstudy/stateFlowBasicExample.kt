package com.example.flowchannelsstudy

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class CounterViewModel : ViewModel() {

    private val _counter = MutableStateFlow<Int>(0);
    val counter = _counter.asStateFlow()

    fun increaseCounter(): Unit {
        _counter.value += 1
    }

    fun decreaseCounter(): Unit {
        _counter.value -= 1
    }

}