package com.example.objectandcomposestudy.helpers

object AnimalHelper {

    fun getAnimalAverageWeight(weights: List<Int>): Double {
        return weights.average()
    }
}