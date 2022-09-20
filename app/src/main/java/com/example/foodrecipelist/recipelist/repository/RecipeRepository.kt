package com.example.foodrecipelist.recipelist.repository

import androidx.lifecycle.LiveData
import com.example.foodrecipelist.recipelist.datasource.RestDataSource
import com.example.foodrecipelist.recipelist.model.Recipes
import com.example.foodrecipelist.recipelist.model.RecipesDao
import javax.inject.Inject

interface RecipeRepository {
    suspend fun getInsertRecipes()
    fun updateItemRecipes(recipes: Recipes)
    fun getAllRecipes(): LiveData<List<Recipes>>
    fun getAllFavorites(): LiveData<List<Recipes>>
    fun getItemRecipes(id: Int): LiveData<Recipes>
    fun getSearch(title:String): LiveData<List<Recipes>>
}

class RecipeRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
    private val recipesDao: RecipesDao
) : RecipeRepository {

    override suspend fun getInsertRecipes() {
        //Se insertan los item traidos del Servicio retrofit  por primera vez
        dataSource.getRecipeList().results.map {
            recipesDao.insertRecipe(
                Recipes(
                    title = it.title,
                    image = it.image, favorite = false
                )
            )
        }


    }

    override fun updateItemRecipes(recipes: Recipes) = recipesDao.updateItem(recipes = recipes)
    override fun getAllRecipes(): LiveData<List<Recipes>> = recipesDao.getAll()
    override fun getAllFavorites(): LiveData<List<Recipes>> = recipesDao.getFavorites(true)
    override fun getItemRecipes(id: Int): LiveData<Recipes> = recipesDao.getItem(idItem = id)
    override fun getSearch(title: String): LiveData<List<Recipes>> = recipesDao.getSearch(title = title)

}