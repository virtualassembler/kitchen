package com.david.cook.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david.cook.data.local.Recipe
import com.david.cook.data.local.RecipeResponse
import com.david.cook.repository.RecipeRepository

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    val soccerLeagueLiveData = MutableLiveData <List<Recipe>>()
    private val soccerLeagueRepository = RecipeRepository(application)

    fun callApi(league:String) {
        soccerLeagueRepository.requestMovieReviewList(league,soccerLeagueLiveData)
        var rr = soccerLeagueRepository;
    }
}
