package com.example.flowchannelsstudy

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.flowchannelsstudy.repos.NotificationEvent
import com.example.flowchannelsstudy.repos.NotificationRepository
import com.example.flowchannelsstudy.viewmodels.Location
import com.example.flowchannelsstudy.viewmodels.LocationViewModel
import com.example.flowchannelsstudy.viewmodels.LoginEvent
import com.example.flowchannelsstudy.viewmodels.LoginViewModel
import com.example.flowchannelsstudy.viewmodels.NotificationsViewModelFactory
import com.example.flowchannelsstudy.viewmodels.ProductsPromoNotificationViewModel
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

    private val loginViewModel: LoginViewModel by viewModels()

    private val locationViewModel: LocationViewModel by viewModels()

    private lateinit var productsPromoNotificationViewModel: ProductsPromoNotificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flow_study)

        productsPromoNotificationViewModel =
            ViewModelProvider(
                this,
                NotificationsViewModelFactory(
                    NotificationRepository()
                )
            ).get(ProductsPromoNotificationViewModel::class.java)

        setContent {
            Column() {
                SimpleButton()
                LoginButton(loginViewModel = loginViewModel)

                UserLocationView(locationViewModel = locationViewModel)

                ProductsBanner(productsPromoNotificationViewModel)
            }
        }

        lifecycleScope.launch {
            loginViewModel.events
                .onStart {
                    Log.d(TAG, "onCreate: loginViewModel on start -- ")
                }
                .onEach {
                    Log.d(TAG, "onCreate: loginViewModel on each  -- ")
                }
                .collect { event ->
                    Log.d(TAG, "onCreate: loginViewModel event collected ---  ")
                    Log.d(TAG, "onCreate: loginViewModel event  --- ${event} ")
                    when (event) {
                        is LoginEvent.Success ->
                            Toast.makeText(
                                this@FlowStudyActivity,
                                event.message,
                                Toast.LENGTH_SHORT
                            ).show()

                        is LoginEvent.Error ->
                            Toast.makeText(
                                this@FlowStudyActivity,
                                "Login ${event.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                    }
                }
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

        // flowEventConsumer()

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

        ///  consumerWithExceptionHandling()

//        SharedFlowProducer.startEmitting()
//        consumeMutableSharedFlow()
//        consumeMutableSharedFlowAnother()//second consumer

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
            //exception in consumer
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

            //single -- throws error when there are more than one value
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

    fun consumeMutableSharedFlow(): Unit {
        lifecycleScope.launch {
            Log.d(TAG, "consumeMutableSharedFlow: First consumer started ")
            SharedFlowProducer.mutableSharedFlow.collect {
                Log.d(TAG, "consumeMutableSharedFlow: consume <<<--- ${it}")
            }
        }
    }

    fun consumeMutableSharedFlowAnother(): Unit {
        lifecycleScope.launch {
            delay(6000)
            Log.d(TAG, "consumeMutableSharedFlow: Second consumer started with delayed 6 seconds ")
            SharedFlowProducer.mutableSharedFlow.collect {
                Log.d(TAG, "consumeMutableSharedFlow: Second consumer <<<----- ${it}")
            }
        }
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

@Composable
fun LoginButton(modifier: Modifier = Modifier, loginViewModel: LoginViewModel) {
    Button(onClick = {
        CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG, "LoginButton: on main thread launch ")
            loginViewModel.onLoginClicked("gufran", "122")
        }
    }) {
        Text(text = "Login")
    }
}

@Composable
fun UserLocationView(modifier: Modifier = Modifier, locationViewModel: LocationViewModel) {

    var locationState = remember { mutableStateOf(Location(0.0, 0.0)) }

    LaunchedEffect(Unit) {
        locationViewModel.locationEvent.collect {
            locationState.value = it
        }
    }

    Text(text = "User Location : ${locationState.value.lat} ,  ${locationState.value.long} ")
}

@Composable
fun ProductsBanner(promoNotificationViewModel: ProductsPromoNotificationViewModel) {
    var productNotificationState = remember { mutableStateOf(NotificationEvent("")) }

    LaunchedEffect(Unit) {
        promoNotificationViewModel.notificationEvent.collect {
            productNotificationState.value = it
        }
    }

    Text(text = "Product Notification ${productNotificationState.value.message}")
}