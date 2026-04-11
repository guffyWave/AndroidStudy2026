package com.example.cheezycodecomposestudy

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Preview
@Composable
private fun AutoCounterPreview() {
    AutoCounter()
}

@Composable
fun AutoCounter(modifier: Modifier = Modifier) {
    //var counterState = remember { mutableStateOf(0) }

    var counterState = produceState(initialValue = 0) {
        for (i in 1..10) {
            delay(1000)
            value += 1 // default variable
        }
    }

    Column(Modifier.padding(60.dp)) {
        NetworkConnectionStatusView()
        Text(
            text = "Hello ${counterState.value}", fontSize = 36.sp,
        )
    }
}


@Preview
@Composable
private fun LoaderPreview() {
    Loader()
}


@Composable
fun Loader(modifier: Modifier = Modifier) {

    var rotationDegree = produceState(initialValue = 0.0) {
        while (true) {
            delay(30)
            value = (value + 5) % 360
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(1f),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.sync),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .rotate(rotationDegree.value.toFloat())
            )
            Text(text = "Loading..")
        }
    }

}


@Composable
fun ConnectivityStatus(context: Context): State<Boolean> {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val connectivityState = produceState(false) {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                value = true
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                value = false
            }
        }
        connectivityManager.registerDefaultNetworkCallback(callback)

        awaitDispose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

    return connectivityState

}

@Composable
fun NetworkConnectionStatusView(modifier: Modifier = Modifier) {
    val isOnline by ConnectivityStatus(LocalContext.current)

    if (isOnline) {
        Text("You are back online!", color = Color.Green, fontSize = 25.sp)
    } else {
        Text("No internet connection", color = Color.Red, fontSize = 25.sp)
    }

}