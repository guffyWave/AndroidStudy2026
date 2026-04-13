package com.example.hiltstudy.helpers

import javax.inject.Inject


class BikeAdventure @Inject constructor(val place: String) {

    fun getAdventurePlace(): String {
        return place
    }

    fun performAdventure() {
        println("Doing Biking Adventure  at ${place}")
    }
}