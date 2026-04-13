package com.example.hiltstudy.viewmodel

import javax.inject.Inject

class AdventureRepository @Inject constructor() {
    fun getAdventure(): String = "Scuba Diving in Bali"
}