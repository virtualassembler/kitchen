package com.david.cook.ui
import com.david.cook.data.local.Recipe

interface RecipeEvents {
    fun onItemClicked(recipe: Recipe)
}
