package com.example.objectandcomposestudy.singletonstudy

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object CoroutineSafeCounter {
    private var mutex = Mutex() //lock
    private var counter: Int = 0

    suspend fun getCounter(): Int {
        return mutex.withLock { counter }
    }

    suspend fun increment(value: Int) {
        mutex.withLock { counter += value }
    }
}