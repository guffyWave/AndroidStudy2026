package com.example.objectandcomposestudy

object PersistenceManager {

    private const val PERSISTENCE_LEVEL = "DISC"

    fun getPersistenceLevel(): String {
        return PERSISTENCE_LEVEL
    }
}