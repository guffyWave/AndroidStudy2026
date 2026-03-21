package com.example.androidarchstudy.management

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

const val TAG = "MainActivityLifeCycleObserver"

class MainActivityLifeCycleObserver : LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d(TAG, "onCreate: ")
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.d(TAG, "onResume: ")
    }

    fun onDestroy() {

    }
}
