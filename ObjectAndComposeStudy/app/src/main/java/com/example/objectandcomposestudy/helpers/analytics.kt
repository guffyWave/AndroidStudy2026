package com.example.objectandcomposestudy.helpers

import android.util.Log
import com.example.objectandcomposestudy.TAG

object AnalyticsLogger {
    private const val LOG_LEVEL = "DEFAULT"

    var provider = "Google"
        get() = field


    fun logMessage(message: String) {
        Log.d(TAG, "logMessage: Logging -- ${message}")

    }
}