package com.example.hiltstudy.helpers

import javax.inject.Inject


//class GenericAdventureInfo @Inject constructor(val adventureLocation: String) {
class GenericAdventureInfo @Inject constructor() {
    fun getAdventureDate(): String {
        return "14th April 2026"
    }

    fun getAdventureLocation(): String {
        return "Kilimajaro"
    }

    fun performAdventure() {
        println("Performing Adventure")
    }
}