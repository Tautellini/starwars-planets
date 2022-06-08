package com.example.starwarsplanets.navigation

sealed class Route(val route: String) {
    object Planets : Route("planets_screen")
    object Planet : Route("planet_screen/{id}") {
        fun passId(id: Long) = "planet_screen/$id"
    }
}