package com.example.androidtesting

class PasswordManager {

    fun isValidPassword(password: String): Boolean {
        if (password === null || password === "" || password.isEmpty()) {
            return false
        }

        if (password.length < 6 && password.length > 15) {
            return false
        }

        return true
    }
}