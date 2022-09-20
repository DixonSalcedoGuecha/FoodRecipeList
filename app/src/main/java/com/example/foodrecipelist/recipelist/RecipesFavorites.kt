package com.example.foodrecipelist.recipelist

import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.foodrecipelist.R
import com.example.foodrecipelist.recipelist.model.Recipes
import com.example.foodrecipelist.recipelist.model.Routes


@Composable
fun MyFavorites(
    navigationController: NavHostController,
    viewModel: FoodRecipeListViewModel = hiltViewModel(),

    ) {

    val recipesFavorite by viewModel.recipeFavorites.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)



    val recipe = Recipes(0,"","",false)
    val recipeUpdate by remember { mutableStateOf(recipe)}



    MyAppFavorite(
        onUpdate = {
            viewModel.updateFavorites(recipeUpdate)
        }, recipeUpdate, recipesFavorite, isLoading, navigationController
    )

}

@Composable
fun MyAppFavorite(

    onUpdate: (Recipes) -> Unit? = null!!,
    recipeUpdate: Recipes,
    recipesFavorite: List<Recipes>,
    isLoading: Boolean,
    navigationController: NavHostController,

    ) {
    Scaffold(
        topBar = {
            TopAppBar  (
                title = {
                    IconButton(onClick = {
                        navigationController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "Search")
                    }
                    Text(text = "Favorites")

                }
            )
        }
    ) {
        LazyColumn {

            var itemCount = recipesFavorite.size
            if (isLoading) itemCount++

            items(count = itemCount) { index ->
                var auxIndex = index;
                if (isLoading) {
                    if (auxIndex == 0)
                        return@items LoadingCardFavorite()
                    auxIndex--
                }
                val recipe = recipesFavorite[auxIndex]



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
fun LoadingCardFavorite() {
    Card(

        shape = RoundedCornerShape(8.dp),
        elevation = 1.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .testTag("loadingCard")
    ) {
        Row(modifier = Modifier.padding(8.dp)) {


            SpacerFavorite()
            Column {
                SpacerFavorite()
                Box {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                        SpacerFavorite()
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
fun SpacerFavorite(size: Int = 8) = Spacer(modifier = Modifier.size(size.dp))
