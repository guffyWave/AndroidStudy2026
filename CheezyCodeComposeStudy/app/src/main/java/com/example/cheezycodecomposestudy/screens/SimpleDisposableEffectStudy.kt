package com.example.cheezycodecomposestudy.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun SimpleDisposableEffectStudy() {

    val context = LocalContext.current

    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                // handle event
            }
        }
        context.registerReceiver(receiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

//    DisposableEffect(Unit) {
//        val listener = SensorEventListener { /* update UI */ }
//
//        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
//
//        onDispose {
//            sensorManager.unregisterListener(listener)
//        }
//    }


//    DisposableEffect(Unit) {
//        val listener = WebSocketListener()
//
//        webSocket.connect(listener)
//
//        onDispose {
//            webSocket.disconnect()
//        }
//    }
}