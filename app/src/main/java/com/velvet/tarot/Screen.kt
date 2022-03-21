package com.velvet.tarot

import androidx.navigation.NavBackStackEntry

sealed class Screen(val route: String) {
    object Feed : Screen(route = "feed")
    object Cards : Screen(route = "cards/{name}") {
        fun createRoute(name: String) = "cards/$name"
        fun getArgumentId(entry: NavBackStackEntry): String {
            return entry.arguments?.getString("name") ?: "empty"
        }
    }
}
