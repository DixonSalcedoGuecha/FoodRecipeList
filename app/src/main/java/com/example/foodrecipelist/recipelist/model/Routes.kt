package com.example.foodrecipelist.recipelist.model

sealed class Routes (val rute:String){
    object List: Routes("List")
    object DetailRecipe: Routes("DetailRecipe/{id}"){
        fun createRoute(id:Int) = "DetailRecipe/$id"
    }
    object Favorites: Routes("Favorites")
    object Search: Routes("Search")
}
