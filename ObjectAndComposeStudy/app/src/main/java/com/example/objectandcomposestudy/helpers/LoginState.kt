package com.example.objectandcomposestudy.helpers

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val username: String) : LoginState()
    data class Error(val message: String) : LoginState()
}

// it returns object based on condition
fun validateLogin(username: String, password: String): LoginState {

    if (username.isNullOrBlank() || password.isNullOrBlank()) {
        return LoginState.Idle
    }

    if (username.equals("gufran") && password.equals("123")) {
        return LoginState.Success("gufran")
    } else {
        return LoginState.Error("Not valid user ")
    }

}