package com.example.androidarchstudy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DinoViewModel() : ViewModel() {
    private val dinoNameLiveDataObject = MutableLiveData<String>()
    val dinoName: LiveData<String>
        get() = dinoNameLiveDataObject
    
    fun updateDinoName(newName: String) {
        dinoNameLiveDataObject.value = newName
    }
}