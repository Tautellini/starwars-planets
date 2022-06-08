package com.example.starwarsplanets.features.planet.usecases

import com.example.starwarsplanets.features.planets.datasources.PlanetsRepository

class GetPlanetUseCase(
    private val repository: PlanetsRepository
) {
    operator fun invoke(id: Long) = repository.getPlanet(id)
}