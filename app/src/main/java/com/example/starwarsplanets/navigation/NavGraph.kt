package com.example.starwarsplanets.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.starwarsplanets.features.planets.PlanetsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Planets.route
    ) {
        composable(route = Route.Planets.route) {
            PlanetsScreen(navController = navController)
        }
    }
}