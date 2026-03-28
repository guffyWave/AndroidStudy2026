package com.example.objectandcomposestudy.helpers

//by default its singleton and lazy
//object ApiClient {
//    val apiKey: String? = null
//}

class ApiClient private constructor(private val apiKey: String) {

    companion object {
        @Volatile
        private var instance: ApiClient? = null

        fun getInstance(apiKey: String): ApiClient {
            return instance ?: synchronized(this) {
                instance ?: ApiClient(apiKey).also { instance = it }
            }
        }
    }
}