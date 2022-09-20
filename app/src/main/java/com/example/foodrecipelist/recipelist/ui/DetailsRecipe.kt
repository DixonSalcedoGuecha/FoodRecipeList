package com.example.foodrecipelist.recipelist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage


@Composable
fun DetailView(
    navigationController: NavHostController,
    id: Int,
    viewModel: DetailsRecipeViewModel = hiltViewModel()
) {

    val recipes by viewModel.getItemRecipe(id).observeAsState()
    println(id)
    println(recipes?.image)
    println(recipes?.title)
    val title = recipes?.title

    //  val recipe = recipes[id]
    println(recipes)

    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier.fillMaxWidth(),

                title = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                            .clickable { navigationController.popBackStack() },
                        tint = Color.White
                    )
                    Text(text = "Detail of the recipe")

                }
            )
        }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center

            ) {

                AsyncImage(
                    model = recipes?.image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop

                )


            }
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.secondary)
                    .padding(16.dp)
            ) {

                Text(
                    text = recipes?.title.toString(),
                    style = MaterialTheme.typography.h6
                )

            }
        }
    }
}




