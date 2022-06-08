package com.example.starwarsplanets.di

import com.example.starwarsplanets.features.planet.PlanetUseCases
import com.example.starwarsplanets.features.planet.usecases.GetPlanetUseCase
import com.example.starwarsplanets.features.planets.datasources.PlanetsRepository
import com.example.starwarsplanets.features.planets.usecases.GetPlanetsUseCase
import com.example.starwarsplanets.features.planets.PlanetsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providePlanetsUseCases(repository: PlanetsRepository) = PlanetsUseCases(
        getPlanets = GetPlanetsUseCase(repository = repository),
        // ...
    )

    @Provides
    fun providePlanetUseCases(repository: PlanetsRepository) = PlanetUseCases(
        getPlanet = GetPlanetUseCase(repository = repository),
        // ...
    )
}