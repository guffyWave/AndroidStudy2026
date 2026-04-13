package com.example.flowchannelsstudy

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

fun flowProducer() = flow<Int> {
    val ageList = listOf<Int>(1, 2, 3, 4, 5, 6, 7)
    ageList.forEach {
        delay(1000)
        emit(it)
    }
}



