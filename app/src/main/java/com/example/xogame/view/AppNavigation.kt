package com.example.xogame.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.xogame.viewModel.GameViewModel


@Composable
fun AppNavigation(
    viewModel: GameViewModel
) {

    val navController = rememberNavController()

    NavHost(navController , startDestination = "MenuScreen" ){
        composable("MenuScreen") {
            MenuScreen("XO GAME", navController = navController)
        }
        composable("GameSetUpScreen") {
            GameSetUpScreen(navController = navController, viewModel=viewModel)
        }
        composable("GameHistoryScreen") {
            GameHistoryScreen(navController = navController, viewModel=viewModel)
        }
        composable("PlayScreen/{boardSize}") {
            backStackEntry -> val boardSize = backStackEntry.arguments?.getString("boardSize")?.toIntOrNull()
            PlayScreen(navController = navController, boardSize = boardSize ?: 3 , viewModel=viewModel)
        }

    }

}