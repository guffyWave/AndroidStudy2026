package com.example.composestudy.coroutineStudy

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocalPersistenceManager(context: Context) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    private val scope = MainScope()

    fun saveString(name: String): Unit {
        scope.launch {
            ///save the data
            try {
                withContext(Dispatchers.IO) {
                    sharedPreferences.edit().putString("NAME", name).apply()
                }
            } catch (e: Exception) {

            }
        }
    }

    fun getString(key: String, defaultValue: String?, onResult: (Result<String?>) -> Unit) {
        scope.launch {
            try {
                // Perform read operation on IO thread
                val result = withContext(Dispatchers.IO) {
                    sharedPreferences.getString(key, defaultValue)
                }
                // Notify result on main thread
                onResult(Result.success(result))
            } catch (e: Exception) {
                // Notify failure on main thread
                onResult(Result.failure(e))
            }
        }
    }

    fun onDestroy(): Unit {
        scope.cancel()
    }


}