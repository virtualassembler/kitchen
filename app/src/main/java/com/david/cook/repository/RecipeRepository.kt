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

    private val apiService = ApiRequest.instance
    private val recipeDatabase: RecipeDao get() = RecipeDatabase.getRecipe(context).getRecipeDAO()

    fun requestMovieReviewList(league: String, liveData: MutableLiveData<List<Recipe>>) {
        apiService.getMovieReviewListFromInternet().enqueue(object : Callback<List<Recipe>> {

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                Log.e("a12","imprima")
                Log.e(TAG_ON_FAILURE, t.printStackTrace().toString())
                Log.e("a11",""+t.printStackTrace().toString())
            }

            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                Log.e("a12","imprima")
                Log.e("a10",""+response.body())
                //Save on db
                insertRecipeListIntoDatabase(response);
                var localRecipeList = getRecipeList();
                val localRecipeList2 = getRecipeList();
                val length = localRecipeList.size
                //get from db straight to view model
                liveData.value = response.body()
            }
        })
    }

    private fun insertRecipeListIntoDatabase(response: Response<List<Recipe>>) {
        if (response.body() != null) {
            for (recipe: Recipe in response.body()!!) {
                recipeDatabase.insertRecipe(recipe)
            }
        }
    }

    fun getRecipeList(): List<Recipe> {
        return recipeDatabase.getRecipeList()
        //return recipeDatabase.getSoccerLeague(context).getTeamEventDAO().getTeamReviewList()
    }

}
