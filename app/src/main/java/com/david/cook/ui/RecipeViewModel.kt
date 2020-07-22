package com.david.cook.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.david.cook.data.local.Recipe
import com.david.cook.repository.RecipeRepository

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    val recipeLiveData = MutableLiveData <List<Recipe>>()
    private val recipeRepository = RecipeRepository(application)

    fun callApi(league:String) {
        recipeRepository.requestRecipeList(recipeLiveData)
    }

    fun getFilteredRecipeList(league:String) {
        recipeRepository.getFilteredRoadReferenceList(league,recipeLiveData)
    }
}
