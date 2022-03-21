package com.velvet.tarot

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.velvet.tarot.card.CardScreen
import com.velvet.tarot.card.CardViewModel
import com.velvet.tarot.feed.FeedScreen
import com.velvet.tarot.feed.FeedViewModel
import com.velvet.tarot.navigation.Destinations
import com.velvet.tarot.theme.AppTheme


@Composable
fun AppScreen() {
    AppTheme {
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(color = Color.Transparent)
        val navController = rememberNavController()
        NavHost(navController, startDestination = Destinations.FEED) {
            composable(Destinations.FEED) { backStackEntry ->
                val feedViewModel = hiltViewModel<FeedViewModel>()
                FeedScreen(viewModel = feedViewModel, navController = navController)
            }
            composable("${Destinations.CARDS}/{name}",
                arguments = listOf(navArgument("name") { type = NavType.StringType })) {
                backStackEntry ->
                val feedViewModel = hiltViewModel<CardViewModel>()
                CardScreen(cardName = backStackEntry.arguments?.getString("name"), viewModel = feedViewModel, navController =  navController)
            }
        }
    }
}

