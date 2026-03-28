package com.example.androidarchstudy.management

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

const val TAG = "MainActivityLifeCycleObserver"

//class MainActivityLifeCycleObserver : LifecycleObserver {
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//    fun onCreate() {
//        Log.d(TAG, "onCreate: ")
//    }
//
//    @OnLifecycleEvent(value = Lifecycle.Event.ON_RESUME)
//    fun onResume() {
//        Log.d(TAG, "onResume: ")
//    }
//
//    fun onDestroy() {
//
//    }
//}

class MainActivityLifeCycleObserver : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        
        Log.d(TAG, "onCreate: ")
    }


    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
    }
}
