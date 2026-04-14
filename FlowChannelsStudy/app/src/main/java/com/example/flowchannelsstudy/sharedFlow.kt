package com.example.flowchannelsstudy

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


object SharedFlowProducer {
    val mutableSharedFlow = MutableSharedFlow<Int>()

    fun startEmitting(): Unit {
        GlobalScope.launch {
            val list = (1..20).toList()
            list.forEach {
                Log.d(TAG, "sharedFlowProducer: emit >>> ${it}")
                mutableSharedFlow.emit(it)
                delay(1000)
            }
        }
    }


}
