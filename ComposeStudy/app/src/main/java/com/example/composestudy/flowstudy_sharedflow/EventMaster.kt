package com.example.composestudy.flowstudy_sharedflow


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

object EventMaster {
    // Centralized shared flow with replay = 0 (event-style)
    private val _eventFlow = MutableSharedFlow<String>(replay = 0)
    val eventFlow: SharedFlow<String> = _eventFlow.asSharedFlow()


    // Helper to fire events from any context
    fun fireEvent(message: String): Unit {
        // Launch in global scope for demonstration (usually use your own scope)

        CoroutineScope(Dispatchers.Default).launch {
            
            _eventFlow.emit(message)
        }
    }
}