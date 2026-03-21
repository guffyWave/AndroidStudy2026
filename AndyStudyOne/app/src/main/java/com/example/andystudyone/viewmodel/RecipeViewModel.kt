package com.example.andystudyone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andystudyone.model.Recipe
import com.example.andystudyone.service.RecipeRepository
import com.example.andystudyone.service.RecipeResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository = RecipeRepository()) : ViewModel() {
    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        fetchRecipes()
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            repository.getRecipes().collect { result ->
                when (result) {
                    is RecipeResult.Loading -> _loading.value = true
                    is RecipeResult.Success -> {
                        _loading.value = false
                        _recipes.value = result.data
                    }
                    is RecipeResult.Error -> {
                        _loading.value = false
                        _error.value = result.message
                    }
                }
            }
        }
    }
}







