package com.example.foodrecipelist.recipelist.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "recipes")
data class Recipes(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "favorite") var favorite: Boolean
)

@Dao
interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipes: Recipes)

    @Query("SELECT * FROM recipes ORDER BY id DESC")
    fun getAll(): LiveData<List<Recipes>>

    @Query("SELECT * FROM recipes WHERE id = :idItem")
    fun getItem(idItem: Int): LiveData<Recipes>

    @Update
    fun updateItem(recipes: Recipes)

    @Query("SELECT * FROM recipes WHERE favorite = :favorite")
    fun getFavorites(favorite: Boolean): LiveData<List<Recipes>>

    @Query("SELECT * FROM recipes WHERE title LIKE '%'||:title||'%'")
    fun getSearch(title: String): LiveData<List<Recipes>>

}
