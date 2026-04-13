package com.example.flowchannelsstudy

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random


//Backpressure Handling (Producer → Consumer)
//Example: Sensor data, camera frames, GPS updates.
//Scenario
//A producer is generating data faster than the consumer can process.
//Solution: Channel with limited capacity
class BackPressureHandler(
    private val scope: CoroutineScope,
    private val onProcessed: (Float) -> Unit
) {

    private val TAG = "BackPressureHandler"

    // Bounded buffer (important for backpressure!)
    private val sensorChannel = Channel<Float>(capacity = 10)


    init {
        startProducer()
        startConsumer()
    }

    private fun startProducer() {
        scope.launch(Dispatchers.Default) {
            while (isActive) {
                val value = readFakeSensor()
                Log.d(TAG, "Produced ----------------------------->>  : ${value}")
                sensorChannel.send(value)
                delay(50) // fast producer
            }
        }
    }

    private fun startConsumer() {
        scope.launch(Dispatchers.Default) {
            for (value in sensorChannel) {
                Log.d(TAG, "Consumed <<: ${value}")

                processSensorData(value)

                withContext(Dispatchers.Main, {
                    onProcessed(value)
                })
                delay(1000) // slow consumer
            }
        }
    }

    fun stop() {
        sensorChannel.close()
        scope.coroutineContext.cancelChildren()
    }


    private fun readFakeSensor(): Float {
        return Random.nextFloat() * 100
    }

    private suspend fun processSensorData(value: Float) {
        delay(100)
    }


}