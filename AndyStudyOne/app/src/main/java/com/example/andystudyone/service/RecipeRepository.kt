package com.example.andystudyone.service

import com.example.andystudyone.model.Recipe
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

sealed class RecipeResult<out T> {
    object Loading : RecipeResult<Nothing>()
    data class Success<T>(val data: T) : RecipeResult<T>()
    data class Error(val message: String) : RecipeResult<Nothing>()
}

class RecipeRepository {
    fun getRecipes(): Flow<RecipeResult<List<Recipe>>> = flow {




        emit(RecipeResult.Loading)
        try {
            //delay(3000)
            val response = RetrofitInstance.api.getRecipes(20)
            emit(RecipeResult.Success(response.recipes))
        } catch (e: Exception) {
            emit(RecipeResult.Error(e.localizedMessage ?: "Unknown Error"))
        }


    }


    suspend fun getData(): String {


        // Simulate delay
        delay(1000)
        return "Data fetched at \${System.currentTimeMillis()}"
    }


}






















