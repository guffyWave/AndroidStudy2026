package com.example.androidtesting

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class PasswordManagerTest {

    lateinit var passwordManager: PasswordManager

    @Before
    fun setUp() {
        passwordManager = PasswordManager()
    }


    @Test
    fun isValidPassword() {
        //arrange
        //Act
        val result = passwordManager.isValidPassword("APPLE1234")
        //assert
        assertEquals(true, result)
    }

}