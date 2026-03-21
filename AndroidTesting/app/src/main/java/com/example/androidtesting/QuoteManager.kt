package com.example.androidtesting

import android.content.Context
import com.google.gson.Gson

class QuoteManager {
    var quoteList = emptyArray<Quote>()
    var currentQouteIndex = 0

    fun populateAssetsFromFile(context: Context, fileName: String) {
        val inputStream = context.assets.open(fileName)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()

        quoteList = gson.fromJson(json, Array<Quote>::class.java)
    }

    fun populateQuotes(quotes: Array<Quote>) {
        quoteList = quotes
    }

    fun getCurrentQuote(): Quote {
        return quoteList.get(currentQouteIndex)
    }

    fun getPreviousQuote(): Quote {
        if (currentQouteIndex == 0) return quoteList.get(currentQouteIndex)
        return quoteList.get(--currentQouteIndex)
    }

    fun getNextQuote(): Quote {
        if (currentQouteIndex == quoteList.size - 1) return quoteList.get(currentQouteIndex)
        return quoteList.get(++currentQouteIndex)
    }

}