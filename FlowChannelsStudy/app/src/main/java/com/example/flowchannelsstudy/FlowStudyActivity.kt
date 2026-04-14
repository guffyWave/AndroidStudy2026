package com.example.flowchannelsstudy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class FlowStudyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flow_study)

        setContent {
            SimpleButton()
        }


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

//        GlobalScope.launch {
//            val data: Flow<Int> = flowProducer()
//            data.collect {
//                Log.d(TAG, "Flow Collect One  -->  ${it}")
//            }
//        }

        //  flowEventConsumer()

        //terminalOperatorsStudy()

        //nonTerminalOperatorsStudy()

        //consuming list as flow
//        lifecycleScope.launch {
//            getNotes()
//                //.onEach { delay(1000) }
//                .collect {
//                    Log.d(TAG, "getNotes as flow : ${it}")
//                }
//        }


        // consumingVeryFastProducer()

        //  consumingVeryFastProducerWithBuffer()

        //  consumingVeryFastProducerWithConflate()


        // consumingVeryFastProducerWithCollectLatest()

        ///  consumeFlowWithThreadLog() // context preservation

        //  consumeFlowOnSeparateThread()

        //consumeFlowOnSeparateThreadWithOperators()

        consumerWithExceptionHandling()

    }

    ///consuming fast producer
    fun consumingVeryFastProducer(): Unit {
        lifecycleScope.launch {
            //without buffer backpressure is created for producer
            fastSensorFlow().collect {
                delay(800)   // Slow consumer (heavy UI/db processing)
                Log.d(TAG, "consumingVeryFastProducer:<<----  ${it}")
            }
        }
        //This slows down the entire Flow.
        // Producer waits for consumer → backpressure is created.
    }

    fun consumingVeryFastProducerWithBuffer(): Unit {
        lifecycleScope.launch {
            fastSensorFlow()
                .buffer()   // Buffer launches producer in parallel
                .collect {
                    delay(800)   // Slow consumer (heavy UI/db processing)
                    Log.d(TAG, "consumingVeryFastProducer:<<----  ${it}")
                }
        }
    }

    fun consumingVeryFastProducerWithConflate(): Unit {
        lifecycleScope.launch {
            fastSensorFlow()
                .conflate()   // Skip older values, keep latest only
                .collect {
                    delay(800)   // Slow consumer (heavy UI/db processing)
                    Log.d(TAG, "consumingVeryFastProducer:<<----  ${it}")
                }
        }
    }

    fun consumingVeryFastProducerWithCollectLatest(): Unit {
        lifecycleScope.launch {
            fastSensorFlow()
                .collectLatest {
                    delay(800)   // Slow consumer (heavy UI/db processing)
                    Log.d(TAG, "consumingVeryFastProducer:<<----  ${it}")
                }
        }
    }

    //Context Preservation example
    fun consumeFlowWithThreadLog(): Unit {
        /// Context Preservation
        // if the consumer is  consuing on main thread then producer will be emmiting on the same thread
        lifecycleScope.launch(Dispatchers.IO) {
            normalProducer().collect {
                Log.d(
                    TAG,
                    "consumeFlowWithThreadLog:<<----  ${it} on thread  ${Thread.currentThread().name}"
                )
            }
        }

    }

    fun consumeFlowOnSeparateThread(): Unit {
        lifecycleScope.launch {
            normalProducer()
                .flowOn(Dispatchers.IO)
                .collect {
                    Log.d(
                        TAG,
                        "consumeFlowWithThreadLog <<----  ${it} on thread ${Thread.currentThread().name}"
                    )
                }
        }
    }

    fun consumeFlowOnSeparateThreadWithOperators(): Unit {
        lifecycleScope.launch {
            normalProducer()
                .map {
                    Log.d(
                        TAG,
                        " <<<map>>>  ${it} on thread ${Thread.currentThread().name}"
                    )
                    it * 2
                }
                .flowOn(Dispatchers.IO) /// all operation above this will be done on IO
                .filter {
                    Log.d(
                        TAG,
                        " <<<filter>>>  ${it} on thread ${Thread.currentThread().name}"
                    )
                    it > 5
                }
                .flowOn(Dispatchers.Default) /// all operation above this will be done on DEfault
                .collect {
                    Log.d(
                        TAG,
                        "consumeFlowWithThreadLog <<----  ${it} on thread ${Thread.currentThread().name}"
                    )
                }
        }
    }

    fun consumerWithExceptionHandling(): Unit {
        try {
            lifecycleScope.launch {
                numberProducer()
                    .collect {
                        Log.d(
                            TAG,
                            "consuming <<----  ${it} "
                        )
                    }
            }
        } catch (e: Exception) {
            Log.d(
                TAG, "consuming exception <<----  ${e.message} "
            )
        }
    }

    //demonstrating events
    fun flowEventConsumer(): Unit {
        GlobalScope.launch {
            flowProducer().onStart {
                //executed when flow begins to collect
                Log.d(TAG, "flowEventConsumer: Begining to collect ")
            }.onEach {
                //before value is passed downstream
                Log.d(TAG, "flowEventConsumer: Value Being Passed  ${it}")
            }.onCompletion {
                //when flow completes to collect
                Log.d(TAG, "flowEventConsumer: Completed  ")
            }.collect {
                Log.d(TAG, "flowEventConsumer: Value Collected --->>   ${it}")
            }
        }

    }

    //terminal operators- are the one which starts the flow the consumption of data
    fun terminalOperatorsStudy(): Unit {
        GlobalScope.launch {

            val res = flowProducer().first()
            Log.d(TAG, "flowProducer:res --->>   ${res}")

            val result = flowProducer().first { it ->
                it > 5
            }
            Log.d(TAG, "flowProducer:result --->>   ${result}")


            ///converts to list - stays suspended untill all the values are  emitted
            val obtainedFlowList = flowProducer().toList()
            obtainedFlowList.forEach {
                Log.d(TAG, "flowProducer:obtainedFlowList --->>   ${it}")
            }

            //signle -- throws error when there are more than one value
            //reduce()
            //fold
            //count
            //set

        }

    }

    fun nonTerminalOperatorsStudy(): Unit {
        GlobalScope.launch {
            flowProducer().map {
                it * 2
            }.filter { it > 6 }
                .collect {
                    Log.d(TAG, "flowProducer:obtainedFlowList --->>   ${it}")
                }
        }
    }

    fun getNotes(): Flow<String> {
        var notes = listOf<String>(
            "This is text",
            "Another Note",
            "Moon looks big",
            "House roof shape is such to get rid of ice"
        )
        return notes.asFlow()
    }

}

@Composable
fun SimpleButton(modifier: Modifier = Modifier) {

    Column(Modifier.padding(50.dp)) {
        Text(text = "Flow Study")
        Button(onClick = {
            CoroutineScope(Dispatchers.Main).launch {
                val data: Flow<Int> = flowProducer()
                data.collect {
                    Log.d(TAG, "Flow Collect Two Button   -->  ${it}")
                }
            }
        }) {
            Text(text = "Collect Data ")
        }
    }
}