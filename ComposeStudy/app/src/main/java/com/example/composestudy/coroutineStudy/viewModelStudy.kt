package com.example.composestudy.coroutineStudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel() : ViewModel() {

    fun loadUsers(): Unit {
        viewModelScope.launch {
        }
    }

    override fun onCleared() {
        super.onCleared()
        
    }
}
