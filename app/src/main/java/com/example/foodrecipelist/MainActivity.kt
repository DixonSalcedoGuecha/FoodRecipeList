package com.example.foodrecipelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodrecipelist.recipelist.ui.DetailView
import com.example.foodrecipelist.recipelist.ui.MyApp1
import com.example.foodrecipelist.recipelist.ui.SearchScreen
import com.example.foodrecipelist.recipelist.ui.MyFavorites
import com.example.foodrecipelist.recipelist.model.Routes
import com.example.foodrecipelist.ui.theme.FoodRecipeListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodRecipeListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val show by rememberSaveable { mutableStateOf(false)}
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Routes.List.rute ){
                        composable(Routes.List.rute){
                            MyApp1(navigationController)
                        }
                        composable(Routes.Favorites.rute){
                            MyFavorites(navigationController)
                        }
                        composable(Routes.DetailRecipe.rute, arguments = listOf(navArgument("id"){type = NavType.IntType})){
                            backStackEntry -> DetailView(
                            navigationController = navigationController ,
                            id =backStackEntry.arguments?.getInt("id") ?:0 )
                        }

                        composable(Routes.Search.rute){
                            SearchScreen(navigationController)
                        }


                    }

                    //RecipeItem()
                    //Greeting("Android")
                }
            }
        }
    }
}

