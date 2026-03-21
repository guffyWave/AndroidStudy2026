package com.example.androidtesting

import com.example.androidtesting.utils.Helper
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HelloTest {

    lateinit var helper: Helper

    @Before
    fun setUp(): Unit {
        println("Before Every Test case ")
        helper = Helper()
    }

    @After
    fun tearDown(): Unit {
        println("After Every Test case ")
    }

    @Test
    fun testPalindrome(): Unit {
        //Arrange
        // val helper = Helper()
        //Act
        val result = helper.isPalindrome("Apple")
        //Assert
        assertEquals(false, result)
    }

    @Test
    fun testIsPalindrome_LEVEL(): Unit {
        //Arrange
        //val helper = Helper()
        //Act
        val result = helper.isPalindrome("level")
        //Assert
        assertEquals(true, result)
    }
}