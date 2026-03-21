package com.example.composestudy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composestudy.container.PersonListContainer
import com.example.composestudy.repository.PersonRepository
import com.example.composestudy.ui.theme.ComposeStudyTheme

@Composable
fun HomeScreen(navController: NavController) {

    val personRespository = PersonRepository()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            modifier = Modifier.clickable {
                navController.navigate(route = Screen.Detail.route + "/John")
            },
            text = "Home",
            color = Color.Red,
            fontSize = 46.sp,
            fontWeight = FontWeight.Bold
        )
        PersonListContainer(personRespository.getAllData())
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ComposeStudyTheme {
        HomeScreen(navController = rememberNavController())
    }
}