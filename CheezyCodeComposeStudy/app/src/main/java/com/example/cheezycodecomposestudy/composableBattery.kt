package com.example.cheezycodecomposestudy

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class BatteryReceiver(
    private val onBatteryLevelChange: (Int) -> Unit
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        onBatteryLevelChange(level)
    }
}

@Composable
fun BatteryListener(onBatteryLevel: (Int) -> Unit) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val receiver = BatteryReceiver(onBatteryLevel)

        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        context.registerReceiver(receiver, intentFilter)

        // Clean up when composable leaves composition
        onDispose {
            context.unregisterReceiver(receiver)
        }
    }
}

@Preview
@Composable
fun BatteryScreen() {
    var batteryLevel = remember { mutableStateOf(0) }

    // Listen to battery changes
    BatteryListener { level ->
        batteryLevel.value = level
    }

    // UI
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "🔋 Battery Level: ${batteryLevel.value}%", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(20.dp))
        LinearProgressIndicator(
            progress = batteryLevel.value / 100f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}