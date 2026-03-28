package com.example.objectandcomposestudy.shared

object SessionManager {
    var authToken: String? = null
        get() = field?.uppercase()
        set(value) {
            if (!value.isNullOrBlank()) {
                field = value
            }
        }
    var userId: String? = null

    fun isLoggedIn(): Boolean {
        if (authToken.isNullOrEmpty()) {
            return false
        }
        return true
    }
}