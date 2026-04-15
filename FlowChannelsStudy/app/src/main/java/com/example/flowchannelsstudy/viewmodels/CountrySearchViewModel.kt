package com.example.flowchannelsstudy.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CountrySearchViewModel : ViewModel() {
    private val _query = MutableStateFlow<String>("")
    val query = _query.asStateFlow()

    fun updateQuery(queryString: String): Unit {
        _query.value = queryString
    }

}