package com.example.cheezycodecomposestudy

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun ConnectivityObserver(context: Context) {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    DisposableEffect(Unit) {
        val listener = { /* handle network change */ }

        //connectivityManager.registerDefaultNetworkCallback(listener)

        onDispose {
            //connectivityManager?.unregisterNetworkCallback(listener)
        }
    }
}