package com.example.flowchannelsstudy

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow


fun numberProducer(): Flow<Int> = flow {
    for (item in 1..10) {
        delay(1000)
        
        if (item == 7) throw Exception("Error occurred")

        emit(item)
        Log.d(TAG, "normalProducer: emmited >> ${item} on thread >> ${Thread.currentThread().name}")
    }
}.catch { e ->
    emit(-1)
}