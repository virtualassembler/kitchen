package com.david.cook.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipes")
    fun getRecipeList(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE title LIKE :roadReferenceHash ORDER BY title DESC")
    fun getFilteredRecipeList(roadReferenceHash: String): List<Recipe>

    @Query("DELETE FROM recipes")
    fun deleteAllRecipes()
}
