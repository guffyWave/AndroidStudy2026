package com.example.composenavigationstudy.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

//NavController vs NavHostController

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    startDestination: String = ScreenItem.Home.route
) {

    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(ScreenItem.Home.route) {
            HomeScreen(navHostController)
        }

        composable(ScreenItem.Details.route) {
            DetailsScreen(navHostController)
        }
    }

}