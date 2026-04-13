package com.example.flowchannelsstudy

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


val channel= Channel<Int>()

fun doMain() {
    producer()
    consumer()
}
fun producer(): Unit {
    CoroutineScope(Dispatchers.Main).launch {

        //either individually send or use for loop
//            channel.send(1)
//            channel.send(2)


        for (i in 1..10) {
            delay(1000)
            channel.send(i)
        }
    }
}

fun consumer(): Unit {
    CoroutineScope(Dispatchers.Main).launch {
        //either individually collect or use for loop
//        Log.d(TAG, "consumer --:  ${ channel.receive().toString()}")
//        Log.d(TAG, "consumer -- :  ${ channel.receive().toString()}")

        while (true){
            Log.d(TAG, "consumer -- :  ${ channel.receive().toString()}")
        }
    }
}
//producer
//consumer