package com.example.composestudy.coroutineStudy

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SomeActivity : ComponentActivity() {

    private var job: Job? = null // Store the Job reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        job = GlobalScope.launch {
            //some work
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
        job = null
    }
}