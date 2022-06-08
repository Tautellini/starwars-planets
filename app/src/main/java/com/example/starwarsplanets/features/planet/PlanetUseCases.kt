package com.example.starwarsplanets.features.planet

import com.example.starwarsplanets.features.planet.usecases.GetPlanetUseCase

data class PlanetUseCases(
    val getPlanet: GetPlanetUseCase
)