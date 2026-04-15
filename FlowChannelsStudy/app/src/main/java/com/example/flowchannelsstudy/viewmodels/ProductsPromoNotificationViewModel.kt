package com.example.flowchannelsstudy.viewmodels

import androidx.lifecycle.ViewModel
import com.example.flowchannelsstudy.repos.NotificationRepository

class ProductsPromoNotificationViewModel(private val notificationRepository: NotificationRepository) :
    ViewModel() {
    val notificationEvent = notificationRepository.notificationEvent
}