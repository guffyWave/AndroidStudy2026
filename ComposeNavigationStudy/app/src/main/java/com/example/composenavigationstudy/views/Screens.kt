package com.example.composenavigationstudy.views

enum class Screen {
    HOME, DETAILS
}

sealed class ScreenItem(val route: String) {
    object Home : ScreenItem(Screen.HOME.name)
    object Details : ScreenItem(Screen.DETAILS.name)
}