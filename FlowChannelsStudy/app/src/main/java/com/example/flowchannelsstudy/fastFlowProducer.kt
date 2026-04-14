package com.example.flowchannelsstudy

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


fun fastSensorFlow(): Flow<Int> = flow {
    var count = 0

    while (count <= 100) {
        emit(count++)
        Log.d(TAG, "fastSensorFlow: emit >> ${count}")
        delay(100) // Producer is very fast
    }
}