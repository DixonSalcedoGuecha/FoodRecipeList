package com.example.foodrecipelist.recipelist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipelist.recipelist.model.Recipes
import com.example.foodrecipelist.recipelist.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodRecipeListViewModel @Inject constructor(
    private val userRepo: RecipeRepository
) : ViewModel() {


    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }


    val isLoading: LiveData<Boolean> get() = _isLoading



    val recipe: LiveData<List<Recipes>> by lazy {
        userRepo.getAllRecipes()
    }

    val recipeFavorites: LiveData<List<Recipes>> by lazy {
        userRepo.getAllFavorites()
    }


    private val _recipeUpdate: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
    val recipeUpdate: LiveData<String> get() = _recipeUpdate

    fun addAllRecipes() {
        if (_isLoading.value == false)
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                userRepo.getInsertRecipes()
                _isLoading.postValue(false)
            }
    }




    fun updateFavorites(recipes: Recipes) {
        println(recipes)
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.updateItemRecipes(recipes)
        }
    }




    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

   val listSearch: LiveData<List<Recipes>> by lazy {
        titleSearch(_title.value.toString())
    }
    fun titleSearch(title:String): LiveData<List<Recipes>>{
        println(title)
          _title.value = title
        if (title.isNotEmpty()){
                userRepo.getSearch(title)
        }
        return userRepo.getSearch(_title.value.toString())

    }


}
