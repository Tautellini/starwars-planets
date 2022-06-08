package com.example.starwarsplanets.di

import com.example.starwarsplanets.datasources.database.Database
import com.example.starwarsplanets.features.planets.datasources.database.dao.PlanetsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DaoModule {

    @Provides
    @Singleton
    fun providePlanetsDao(db: Database): PlanetsDao = db.planetsDao()
}