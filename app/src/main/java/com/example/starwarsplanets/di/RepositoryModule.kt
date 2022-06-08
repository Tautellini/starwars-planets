package com.example.starwarsplanets.di

import com.example.starwarsplanets.datasources.database.Database
import com.example.starwarsplanets.features.planets.datasources.PlanetsRepository
import com.example.starwarsplanets.features.planets.datasources.network.PlanetsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePlanetsRepository(
        api: PlanetsApi,
        database: Database
    ): PlanetsRepository = PlanetsRepository(api, database)
}