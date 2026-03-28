package com.example.objectandcomposestudy

import java.util.Date

//Global Utilities
object DateUtils {
    fun convertDateToFriendlyDate(date: Date): String {
        return date.toString()
    }
}


//Any object with utility functions

object ImageDimensionHelper {
    fun calculateImageArea(width: Int, height: Int): Int {
        return width + height
    }

    fun getAspectRatio(width: Float, height: Float): Float {
        return (height / width)
    }
}


