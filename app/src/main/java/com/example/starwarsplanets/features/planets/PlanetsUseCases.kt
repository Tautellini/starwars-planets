package com.example.starwarsplanets.features.planets

import com.example.starwarsplanets.features.planets.usecases.GetPlanetsUseCase

data class PlanetsUseCases(
    val getPlanets: GetPlanetsUseCase
)