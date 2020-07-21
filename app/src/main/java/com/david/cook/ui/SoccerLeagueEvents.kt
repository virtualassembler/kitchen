package com.david.cook.ui
import com.david.cook.data.local.Recipe

interface SoccerLeagueEvents {
    fun onItemClicked(recipe: Recipe)
}
