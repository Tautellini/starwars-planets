package com.example.starwarsplanets.navigation

sealed class Route(val route: String) {
    object Planets : Route("planets_screen")
}