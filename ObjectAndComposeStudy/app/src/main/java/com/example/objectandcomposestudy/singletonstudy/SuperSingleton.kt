package com.example.objectandcomposestudy.singletonstudy

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

class SuperSingleton private constructor() {

    private val requestCount = AtomicInteger(0)  // High‑frequency counters
    private val cache = ConcurrentHashMap<String, String>()  // Shared cache
    private val mutex = Mutex()  // coroutine safe lock

    private var currentUser: String? = null  //protecting mutable state

    @Volatile
    private var counter: Int = 0 // protecting mutable state

    @Synchronized
    fun getCounterValue(): Int {
        return counter
    }

    @Synchronized
    fun updateCounter(_counter: Int) {
        this.counter = _counter
    }

    suspend fun setCurrentUser(user: String) {
        mutex.withLock {
            currentUser = user
        }
    }

    suspend fun getCurrentUser(): String? {
        return mutex.withLock { currentUser }
    }

    companion object {
        @Volatile
        private var instance: SuperSingleton? = null

        fun getInstance(): SuperSingleton {
            return instance ?: synchronized(this) {
                instance ?: SuperSingleton().also { instance = it }
            }
        }
    }
}

