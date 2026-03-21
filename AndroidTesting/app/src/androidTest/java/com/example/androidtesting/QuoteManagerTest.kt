package com.example.androidtesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.gson.JsonSyntaxException
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.io.FileNotFoundException


class QuoteManagerTest {

    @Test(expected = FileNotFoundException::class)
    //@Test(expected = JsonSyntaxException::class)
    fun testPopulateAssetsFromFile() {
        //arrange
        val quoteManager = QuoteManager();
        val context = ApplicationProvider.getApplicationContext<Context>()
        //Act
        quoteManager.populateAssetsFromFile(context, "")
        //quoteManager.populateAssetsFromFile(context, "malformed.json") //
        //Act
    }

    @Test(expected = JsonSyntaxException::class)
    fun testPopulateAssetsFromFile_InvalidJSON() {
        val quoteManager = QuoteManager();
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateAssetsFromFile(context, "malformed.json") //
    }

    @Test
    fun testPopulateAssetsFromFile_QuoteListSize() {
        val quoteManager = QuoteManager();
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateAssetsFromFile(context, "qoutes.json")
        assertEquals(5, quoteManager.quoteList.size)
    }

}