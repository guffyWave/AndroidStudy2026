package com.example.objectandcomposestudy.singletonstudy

//initialisation is thread-safe , but mutables in it are not
object SafeCounter {
    @Volatile
    private var counter: Int = 0

    @Synchronized
    fun getCounterValue(): Int {
        return counter
    }

    @Synchronized
    fun updateCounter(_counter: Int) {
        this.counter = _counter
    }
}