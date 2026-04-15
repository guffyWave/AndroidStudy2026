package com.example.flowchannelsstudy.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.flowchannelsstudy.TAG
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

sealed class LoginEvent {
    data class Success(val message: String) : LoginEvent()
    data class Error(val message: String) : LoginEvent()
}

class LoginViewModel : ViewModel() {
    private val _events = MutableSharedFlow<LoginEvent>()
    val events = _events.asSharedFlow()

    suspend fun onLoginClicked(username: String, password: String) {
        Log.d(TAG, "LoginViewModel: onLoginClicked -- ")

        if (username == "admin" && password == "1234") {
            _events.emit(LoginEvent.Success("Login Successful!"))
        } else {
            _events.emit(LoginEvent.Error("Invalid credentials"))
        }


        Log.d(TAG, "LoginViewModel: onLoginClicked post emit -- ")

    }

}