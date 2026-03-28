package com.example.objectandcomposestudy.helpers

object AquariumHelper {
    private var marineAnimals = mutableListOf<String>("Seal", "Shark", "Penguin")

    fun getMarineAnimals(): List<String> {
        return marineAnimals.toList()  // returns te copy and not the oroginal list
    }
}