package com.example.flowchannelsstudy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flowchannelsstudy.repos.NotificationRepository

class NotificationsViewModelFactory(val notificationRepository: NotificationRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductsPromoNotificationViewModel(notificationRepository) as T
    }
}