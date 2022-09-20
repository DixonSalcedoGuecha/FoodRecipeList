package com.example.foodrecipelist.recipelist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.foodrecipelist.R
import com.example.foodrecipelist.recipelist.model.Recipes
import com.example.foodrecipelist.recipelist.model.Routes
import com.example.foodrecipelist.ui.theme.FoodRecipeListTheme


@Composable
fun MyApp1(
    navigationController: NavHostController,
    viewModel: FoodRecipeListViewModel = hiltViewModel(),

    ) {
    val recipes by viewModel.recipe.observeAsState(arrayListOf())
    val recipesFavorite by viewModel.recipeFavorites.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)


    //val onChangeScreen by viewModel.onChangedScreen("").obse
    val recipe = Recipes(0, "", "", false)
    val recipeUpdate by remember { mutableStateOf(recipe) }



    MyApp(
        onInsertAll = {
            viewModel.addAllRecipes()
        }, onUpdate = {
            viewModel.updateFavorites(recipeUpdate)
        }, recipeUpdate, recipes, isLoading, navigationController
    )

}

@Composable
fun MyApp(
    onInsertAll: (() -> Unit)? = null,
    onUpdate: (Recipes) -> Unit? = null!!,
    recipeUpdate: Recipes,
    recipes: List<Recipes>,
    isLoading: Boolean,
    navigationController: NavHostController,

    ) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                   Text(text = "List of Recipes")
                },
                actions = {
                    IconButton(onClick = {
                        navigationController.navigate(Routes.Search.rute)
                    }) {
                        Icon(Icons.Filled.Search, "Search")
                    }
                    IconButton(onClick = {
                        navigationController.navigate(Routes.Favorites.rute)
                    }) {
                        Icon(Icons.Filled.Favorite, "Favorite")
                    }
                }
            )
        }
    ) {
        LazyColumn {

            var itemCount = recipes.size
            if (isLoading) itemCount++

            items(count = itemCount) { index ->
                var auxIndex = index;
                if (isLoading) {
                    if (auxIndex == 0)
                        return@items LoadingCard()
                    auxIndex--
                }
                val recipe = recipes[auxIndex]



                Surface(modifier = Modifier.clickable {
                    navigationController.navigate(Routes.DetailRecipe.createRoute(recipe.id))
                }) {


                    Card(
                        shape = RoundedCornerShape(8.dp),
                        elevation = 1.dp,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.padding(8.dp)) {
                            AsyncImage(
                                modifier = Modifier.size(50.dp),
                                model = recipe.image,
                                contentDescription = null,
                                contentScale = ContentScale.FillHeight
                            )
                            Spacer()
                            Column(
                                Modifier.weight(1f),
                            ) {
                                Text(recipe.title)
                            }
                            Spacer()
                            IconButton(onClick = {

                                recipeUpdate.id = recipe.id
                                recipeUpdate.image = recipe.image
                                recipeUpdate.title = recipe.title
                                recipeUpdate.favorite = !recipe.favorite
                                onUpdate.invoke(recipeUpdate)


                            }) {
                                if (recipe.favorite) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_favorite_selected),
                                        contentDescription = null
                                    )

                                } else {
                                    Icon(Icons.Filled.FavoriteBorder, "Favorite")
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun LoadingCard() {
    Card(

        shape = RoundedCornerShape(8.dp),
        elevation = 1.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .testTag("loadingCard")
    ) {
        Row(modifier = Modifier.padding(8.dp)) {


            Spacer()
            Column {
                Spacer()
                Box {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                        Spacer()
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                    }
                }
            }

        }
    }
}


@Composable
fun Spacer(size: Int = 8) = Spacer(modifier = Modifier.size(size.dp))

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FoodRecipeListTheme {

    }
}