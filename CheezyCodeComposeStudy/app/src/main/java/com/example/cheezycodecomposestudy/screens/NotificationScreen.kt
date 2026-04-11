package com.example.cheezycodecomposestudy.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cheezycodecomposestudy.R

@Preview
@Composable
fun NotificationScreen(modifier: Modifier = Modifier) {

    val notificationCount = remember { mutableStateOf(0) }

    Column() {
        NotificationCounter(notificationCount.value, {
            notificationCount.value++
        })
        Spacer(modifier = Modifier.height(50.dp))
        MessageBar(notificationCount.value)
    }

}


@Composable
fun NotificationCounter(notificationCount: Int = 0, onButtonClicked: () -> Unit) {

    Column() {
        Text(text = "You have sent ${notificationCount} notifications")
        Button(onClick = {
            onButtonClicked()
        }) {
            Text(text = "Send Notifications")
        }
    }
}


@Composable
fun MessageBar(notificationCount: Int = 0) {

    Card(
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(3.dp),
        modifier = Modifier

            // .height(50.dp)
            .background(Color.White)

    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(4.dp)) {
            Image(
                painter = painterResource(R.drawable.barclay),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )

            Image(
                imageVector = Icons.Filled.Home,
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .rotate(45F)
            )
            Text(text = "Message Sent so Far ${notificationCount} ")
        }
    }
}
