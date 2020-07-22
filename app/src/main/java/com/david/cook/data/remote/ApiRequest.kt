package com.david.cook.data.remote

import com.david.cook.data.local.Recipe
import com.david.cook.data.local.RecipeResponse
import com.david.cook.utils.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    @GET(SEARCH_ALL_RECIPES)
    fun getRecipeListFromApi(): Call<List<Recipe>>

    companion object {
        val instance: ApiRequest = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiRequest::class.java)
    }
}
