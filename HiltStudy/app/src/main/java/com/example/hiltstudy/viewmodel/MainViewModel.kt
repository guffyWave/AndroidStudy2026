package com.example.hiltstudy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: AdventureRepository,
    @ApplicationContext val context: Context,
) : ViewModel() {
    fun getInfo(): String {
        val extra = context.getString(android.R.string.ok) // example usage
        return "Repo says: ${repository.getAdventure()} "
    }

}