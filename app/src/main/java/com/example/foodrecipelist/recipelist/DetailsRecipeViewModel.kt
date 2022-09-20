package com.example.foodrecipelist.recipelist


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.foodrecipelist.recipelist.model.Recipes
import com.example.foodrecipelist.recipelist.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsRecipeViewModel @Inject constructor(
    private val userRepo: RecipeRepository
) : ViewModel() {


    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val recipe: LiveData<List<Recipes>> by lazy {
        userRepo.getAllRecipes()
    }


    val isLoading: LiveData<Boolean> get() = _isLoading



    fun getItemRecipe(id: Int) : LiveData<Recipes> {
        return   userRepo.getItemRecipes(id)
    }




}
