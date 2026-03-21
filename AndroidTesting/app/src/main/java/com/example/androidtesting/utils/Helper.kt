package com.example.androidtesting.utils

class Helper {

    fun isPalindrome(inputString: String): Boolean {
        return inputString == inputString.reversed()
    }
}