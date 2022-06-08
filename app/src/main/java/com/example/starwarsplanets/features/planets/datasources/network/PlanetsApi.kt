package com.example.starwarsplanets.features.planets.datasources.network

import com.example.starwarsplanets.features.planets.datasources.network.model.PlanetsRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetsApi {

    @GET("api/planets/")
    suspend fun getPlanets(
        @Query("page") number: Int
    ): Response<PlanetsRemote>
}