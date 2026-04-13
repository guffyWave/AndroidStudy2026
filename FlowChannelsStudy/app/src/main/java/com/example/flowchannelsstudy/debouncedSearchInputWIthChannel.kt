package com.example.flowchannelsstudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchInputViewModel : ViewModel() {

    //Channel.CONFLATED only keeps the latest value
    private val queryChannel = Channel<String>(Channel.CONFLATED)


    init {
        viewModelScope.launch {
            for (query in queryChannel) {
                delay(3000)
                //search(query)
            }
        }
    }

    fun onQueryChanged(queryString: String) {
        viewModelScope.launch {
            queryChannel.send(queryString)
        }
    }

}