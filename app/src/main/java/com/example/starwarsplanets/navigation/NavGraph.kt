package com.example.starwarsplanets.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.starwarsplanets.features.planet.presentation.PlanetScreen
import com.example.starwarsplanets.features.planets.presentation.PlanetsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Planets.route
    ) {
        composable(route = Route.Planets.route) {
            PlanetsScreen(navController = navController)
        }
        composable(
            route = Route.Planet.route,
            arguments = listOf(navArgument("id") {
                type = NavType.LongType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getLong("id")
                ?.let { PlanetScreen(it, navController = navController) }
        }
    }
}