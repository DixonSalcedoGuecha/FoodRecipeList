package com.example.foodrecipelist.recipelist.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodrecipelist.recipelist.model.Recipes
import com.example.foodrecipelist.recipelist.model.RecipesDao

@Database(entities = [Recipes::class], version = 1 )
abstract class DbDataSource : RoomDatabase(){

    abstract fun recipesDao(): RecipesDao
}