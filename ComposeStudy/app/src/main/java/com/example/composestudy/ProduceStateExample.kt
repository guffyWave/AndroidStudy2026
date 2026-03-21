package com.example.composestudy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


@Composable
fun RandomUserNameScreen(modifier: Modifier = Modifier) {
    val userName by produceState("No Name") {
        value = fetchRandomUserName()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = userName,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
@Preview
private fun RandomUserNameScreenPreview() {
    RandomUserNameScreen()
}

suspend fun fetchRandomUserName(): String = withContext(Dispatchers.IO) {

    try {
        delay(4000)

        val names = listOf(
            "Gufran",
            "Farhan",
            "Kosov",
            "Madeha",
            "Kotaba",
            "Elepohant",
            "Noman",
            "Natalie",
            "Jason",
            "Koshum"
        )
        names.random()
        
//        val url = URL("https://randomuser.me/api/")
//        val connection = url.openConnection() as HttpURLConnection
//        connection.requestMethod = "GET"
//
//        val response = connection.inputStream.bufferedReader().use { it.readText() }
//        connection.disconnect()
//
//        val jsonObject = JSONObject(response)
//        val resultsArray = jsonObject.getJSONArray("results")
//        val firstResult = resultsArray.getJSONObject(0)
//        val nameObject = firstResult.getJSONObject("name")
//
//        val title = nameObject.getString("title")
//        val first = nameObject.getString("first")
//        val last = nameObject.getString("last")
//
//        "$title $first $last"
    } catch (e: Exception) {
        e.printStackTrace()
        "Error fetching name"
    }

}

