package com.example.starwarsplanets.features.planets.usecases

import com.example.starwarsplanets.features.planets.datasources.PlanetsRepository

class GetPlanetsUseCase(
    private val repository: PlanetsRepository
) {
    operator fun invoke() = repository.getPlanets()
}