package com.velvet.tarot

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.velvet.tarot.fate.FateScreen
import com.velvet.tarot.fate.FateViewModel
import com.velvet.tarot.navigations.Destinations.startRoute
import com.velvet.tarot.theme.AppTheme


@Composable
fun AppScreen() {
    AppTheme() {
            val navController = rememberNavController()
            NavHost(navController, startDestination = startRoute) {
                composable("fate") { backStackEntry ->
                    val fateViewModel = hiltViewModel<FateViewModel>()
                    FateScreen(fateViewModel)
                }
            }
    }
}

