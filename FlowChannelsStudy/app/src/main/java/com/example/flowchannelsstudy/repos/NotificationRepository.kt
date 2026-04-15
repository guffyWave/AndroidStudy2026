package com.example.flowchannelsstudy.repos

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

data class NotificationEvent(val message: String)

class NotificationRepository {
    private val _notificationEvents = MutableSharedFlow<NotificationEvent>()
    val notificationEvent = _notificationEvents.asSharedFlow()

    init {
        generateNotifications()
    }

    fun generateNotifications() {
        
        GlobalScope.launch {
            while (true) {
                val randomTime = (1..5).random()
                delay(randomTime * 1000L)
                _notificationEvents.emit(NotificationEvent("Your Notification ${randomTime} "))
            }
        }
    }
}