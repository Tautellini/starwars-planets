package com.example.starwarsplanets.features.planets.usecases

import com.example.starwarsplanets.features.planets.domain.PlanetsRepository

class GetAllPlanetsUseCase(private val repository: PlanetsRepository) {
    operator fun invoke() = repository.getAllPlanets()
}