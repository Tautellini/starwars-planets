package com.example.starwarsplanets.features.planets.datasources

import com.example.starwarsplanets.features.planets.datasources.network.PlanetRemote
import retrofit2.http.GET

interface PlanetApi {
    @GET("planets")
    suspend fun getAllPlanets(): List<PlanetRemote>
}