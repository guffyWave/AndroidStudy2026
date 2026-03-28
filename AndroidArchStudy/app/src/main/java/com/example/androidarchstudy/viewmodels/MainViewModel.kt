package com.example.androidarchstudy.viewmodels

import androidx.lifecycle.ViewModel

class MainViewModel(val initalValue: Int = 0) : ViewModel() {
    var count: Int = initalValue

    fun increment() {
        count++
    }
}
