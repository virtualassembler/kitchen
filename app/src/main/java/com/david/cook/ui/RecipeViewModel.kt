package com.david.cook.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.david.cook.data.local.Recipe
import com.david.cook.repository.RecipeRepository

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    val recipeLiveData = MutableLiveData <List<Recipe>>()
    private val recipeRepository = RecipeRepository(application)

    fun getRecipeList() {
        recipeLiveData.value = recipeRepository.requestRecipeList()
        var jaja = recipeLiveData.toString()
        var jaja2 = recipeLiveData.toString()
    }

    fun getFilteredRecipeList(searchReference:String) {
        recipeLiveData.value = recipeRepository.getFilteredRoadReferenceList(searchReference)
        var jaja = recipeLiveData.toString()
        var jaja2 = recipeLiveData.toString()
    }

    fun getRecipeListLiveData(): LiveData<List<Recipe>> {
        var jaja = recipeLiveData.toString()
        var jaja2 = recipeLiveData.toString()
        return recipeLiveData
    }
}
