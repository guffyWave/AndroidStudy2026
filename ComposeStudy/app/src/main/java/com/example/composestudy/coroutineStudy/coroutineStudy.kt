package com.example.composestudy.coroutineStudy

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

fun main() {
    // suspended function should be called only from a coroutine or another suspend function
    //printGreeting()


    //Runs a new coroutine and blocks the current thread interruptibly until its completion.
//    println("Before runBlocking")
//    runBlocking {
//        println("Inside runBlocking start")
//        delay(1000L)
//        showFruits()
//        println("Inside runBlocking end after delay")
//    }
//    println("After runBlocking")


    println("Before calling launch")
    //launch - starts a new coroutine , don't return result
    GlobalScope.launch {
        println("Inside launch start")
        delay(1000L)
        showFruits()
        println("Inside launch end after delay")
    }
    println("After launch")


/////---------------->>

    runBlocking(context = Dispatchers.Main) {
        showFruits()
        println("Context" + coroutineContext)
    }


    //builds coroutine
    //GlobalScope.async {  }

    //build coroutine without blocking the current thread and returns coroutine reference as job
//    GlobalScope.launch {
//        showFruits()
//    }


//    CoroutineScope(Dispatchers.IO).launch {
//        showFruits()
//    }

}

suspend fun printGreeting() {
    println("Hello ! ")
}


suspend fun showFruits() {
    for (fruit in listOf("Apple", "Banana", "Guava", "Mondak")) {
        delay(1000L)
        println(fruit)
    }
}

suspend fun countNumbers() {
    for (item in 1..10) {
        println(item)
    }
}
