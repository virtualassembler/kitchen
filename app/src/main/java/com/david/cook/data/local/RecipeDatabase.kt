package com.david.cook.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], version = 4, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun getRecipeDAO(): RecipeDao

    companion object {
        fun getRecipe(context: Context) = Room.databaseBuilder(context.applicationContext,
            RecipeDatabase::class.java, "CookDatabase")
            .allowMainThreadQueries()
            .build()
    }
}
