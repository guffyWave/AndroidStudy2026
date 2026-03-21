package com.example.kotlinstudygufran.basic

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun fetchUser(): String {
    delay(2000);
    return "User fetched "
}


suspend fun fetchPosts(): String {
    delay(5000);
    return "Posts fetched "
}


fun main() {
    println("Start of Coroutine 1   --->");

    runBlocking {
        println("Start of runBlocking");
        launch {
            delay(2000)
            println("Coroutine Done");
        }
        println("End of runBlocking");
    }

    println("End of Courotine 1 --->");

    println("Start of Courotine 2   --->");


    runBlocking {
        val usersDeffered = async { fetchUser() }
        val postsDeffered = async { fetchPosts() }
        val users = usersDeffered.await();
        val posts = postsDeffered.await();
        println("Users and posts fetched - " + users + " " + posts)
    }

    println("End of Courotine 2   --->");
}