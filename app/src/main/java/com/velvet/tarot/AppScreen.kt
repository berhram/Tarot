package com.velvet.tarot

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.velvet.tarot.fate.FateScreen
import com.velvet.tarot.fate.FateViewModel
import com.velvet.tarot.navigations.Destinations.startRoute


@Composable
fun AppScreen() {
        Scaffold() {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = Color.Transparent)
            val navController = rememberNavController()
            NavHost(navController, startDestination = startRoute) {
                composable("fate") { backStackEntry ->
                    val fateViewModel = hiltViewModel<FateViewModel>()
                    FateScreen(fateViewModel)
                }
            }
        }
}

