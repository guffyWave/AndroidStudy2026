package com.example.composestudy.coroutineStudy

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


suspend fun fetchUser(): String {
    delay(1000)
    return "User Data"
}

suspend fun fetchPosts(): String {
    delay(2000L)
    return "Post Data"
}


fun main() = runBlocking {
    println("Started the main ... ")

//    val start = System.currentTimeMillis()
//
//    val defferedUser = async { fetchUser() }
//    val defferedPosts = async { fetchPosts() }
//
//    val combined = defferedUser.await() + defferedPosts.await()
//    println(combined)
//
//    println("Completed in ${System.currentTimeMillis() - start} ms")

    println("---------------------")

//    val defferedTask = async {
//        println("Task Started..")
//        delay(2000)
//        "Task Done"
//    }
//
//    println("Before Deffered await")
    /// println("Deffered Result : ${defferedTask.await()}")

    println("---------------------")

    val lazyTask = async(start = CoroutineStart.LAZY) {
        println("Lazy Task Started..")
        delay(2000)
        "Lazy Task Done"
    }

    println("Before Lazy Deffered await")

}


