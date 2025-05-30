package com.alpha.kotlinbasictoadvancedcourse.retrofit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel: DataViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route){
        composable(route = Screen.RecipeScreen.route){
            RecipeScreen(viewState = viewState, navigateToDetail = {
                //passing data to detail screen
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable (route = Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("cat") ?: Category("","","","")
            CategoryDetailScreen(category = category)
        }
    }
}