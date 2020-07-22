package com.david.cook.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.david.cook.data.local.Recipe
import com.david.cook.data.local.RecipeDao
import com.david.cook.data.local.RecipeDatabase
import com.david.cook.data.remote.ApiRequest
import com.david.cook.utils.TAG_ON_FAILURE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeRepository(private val context: Context) {

    private val recipeDatabase: RecipeDao get() = RecipeDatabase.getRecipe(context).getRecipeDAO()

    fun requestRecipeList(): List<Recipe> {
        ApiRequest.instance.getMovieReviewListFromInternet().enqueue(object : Callback<List<Recipe>> {

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                Log.e("a12","entro en onfailure")
                Log.e(TAG_ON_FAILURE, t.printStackTrace().toString())
                Log.e("a11",""+t.printStackTrace().toString())
            }

            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                Log.e("a12","entro en onresponse")
                Log.e("a10",""+response.body())
                insertRecipeListIntoDatabase(response);
            }
        })
        return recipeDatabase.getRecipeList()
    }

    private fun insertRecipeListIntoDatabase(response: Response<List<Recipe>>) {
        if (response.body() != null) {
            for (recipe: Recipe in response.body()!!) {
                recipeDatabase.insertRecipe(recipe)
            }
        }
    }

    fun getFilteredRoadReferenceList(roadReferenceHash: String): List<Recipe> {
        return recipeDatabase.getFilteredRecipeList("%$roadReferenceHash%")
    }
}
