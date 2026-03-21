package com.example.composestudy

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun AppNavHost(navHostController: NavHostController) {

//    NavHost(navController = navHostController, startDestination = Screen.Home.route, builder = {
//        //NavGraphBuilder  - the builder used to construct the graph
//    })

    NavHost(navController = navHostController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navHostController)
        }
        composable(route = Screen.Detail.route + "/{name}") {
            val name = it.arguments?.getString("name")
            Log.d("AppNavHost", "Arg-  " + name)
            DetailScreen(navHostController)
        }
    }

}