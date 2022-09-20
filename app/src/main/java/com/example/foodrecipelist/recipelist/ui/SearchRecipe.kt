package com.example.foodrecipelist.recipelist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.foodrecipelist.R
import com.example.foodrecipelist.recipelist.model.Recipes
import com.example.foodrecipelist.recipelist.model.Routes


@Composable
fun SearchScreen(
    navigationController: NavHostController,
    viewModel: FoodRecipeListViewModel = hiltViewModel(),

    ) {


    MyAppSearch(viewModel, navigationController)

}

@Composable
fun MyAppSearch(
    viewModel: FoodRecipeListViewModel,
    navigationController: NavHostController,

    ) {

    val titleSearch: String by viewModel.title.observeAsState("")
    val recipes by viewModel.listSearch.observeAsState(mutableListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)


    //val onChangeScreen by viewModel.onChangedScreen("").obse
    val recipe = Recipes(0, "", "", false)
    val recipeUpdate by remember { mutableStateOf(recipe) }

    Scaffold(


        topBar = {
            TopAppBar(
                title = {
                    IconButton(onClick = {
                        navigationController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }

                    SearchInput(titleSearch) {

                        viewModel.titleSearch(it)

                    }
                },
                actions = {

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
                        return@items LoadingCardSearch()
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

                                if (recipe.id!=0){
                                    recipeUpdate.id = recipe.id
                                    recipeUpdate.image = recipe.image
                                    recipeUpdate.title = recipe.title
                                    recipeUpdate.favorite = !recipe.favorite
                                    viewModel.updateFavorites(recipeUpdate)
                                }



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
fun SearchInput(title: String, onTextChange: (String) -> Unit) {
    TextField(
        value = title,
        onValueChange = { onTextChange(it) },
        label = {
            Text(text = "Search")
        },
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(1.dp)
            .background(color = MaterialTheme.colors.surface),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        },
        textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0XFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA)
        )
    )

}


@Composable
fun LoadingCardSearch() {
    Card(

        shape = RoundedCornerShape(8.dp),
        elevation = 1.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .testTag("loadingCard")
    ) {
        Row(modifier = Modifier.padding(8.dp)) {


            SpacerSearch()
            Column {
                SpacerSearch()
                Box {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                        SpacerSearch()
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
fun SpacerSearch(size: Int = 8) = Spacer(modifier = Modifier.size(size.dp))
