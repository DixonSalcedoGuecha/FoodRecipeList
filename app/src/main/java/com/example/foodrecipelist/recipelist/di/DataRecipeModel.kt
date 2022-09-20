package com.example.foodrecipelist.recipelist.di

import android.content.Context
import androidx.room.Room
import com.example.foodrecipelist.recipelist.datasource.DbDataSource
import com.example.foodrecipelist.recipelist.datasource.RestDataSource
import com.example.foodrecipelist.recipelist.model.RecipesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataRecipeModel {

    @Singleton
    @Provides
    @Named("BaseUrl")
    fun providesBaseUrl() = "https://api.spoonacular.com/recipes/"

    @Singleton
    @Provides
    fun providerRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Singleton
    @Provides
    fun restDataSource(retrofit: Retrofit): RestDataSource =
        retrofit.create(RestDataSource::class.java)

    @Singleton
    @Provides
    fun dbDataSource(@ApplicationContext context: Context): DbDataSource {
        return Room.databaseBuilder(context,DbDataSource::class.java, "recipe_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun recipeDao(db: DbDataSource): RecipesDao = db.recipesDao()


}