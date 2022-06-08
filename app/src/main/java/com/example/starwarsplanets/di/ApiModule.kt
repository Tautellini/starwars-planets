package com.example.starwarsplanets.di

import com.example.starwarsplanets.features.planets.datasources.network.PlanetsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providePlanetApi(retrofit: Retrofit): PlanetsApi {
        return retrofit.create(PlanetsApi::class.java)
    }

}