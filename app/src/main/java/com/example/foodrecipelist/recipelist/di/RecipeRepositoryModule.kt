package com.example.foodrecipelist.recipelist.di

import com.example.foodrecipelist.recipelist.repository.RecipeRepository
import com.example.foodrecipelist.recipelist.repository.RecipeRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RecipeRepositoryModule {

    @Binds
    @Singleton
    abstract fun recipeRepository(repo: RecipeRepositoryImp): RecipeRepository
}