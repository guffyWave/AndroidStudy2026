package com.example.composestudy

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composestudy.ui.theme.ComposeStudyTheme

@Composable
fun DetailScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.clickable {
                navController.popBackStack()
                //in oder to pass data back to HomeScreen
                navController.navigate(Screen.Home.route) {

                    popUpTo(Screen.Home.route) { //helps to popup till to mention screen
                        inclusive = true
                    }
                }
            },
            text = "Details",
            color = Color.Blue,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    ComposeStudyTheme {
        DetailScreen(navController = rememberNavController())
    }
}