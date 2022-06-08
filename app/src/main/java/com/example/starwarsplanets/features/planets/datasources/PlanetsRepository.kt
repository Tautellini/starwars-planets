package com.example.starwarsplanets.features.planets.datasources

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwarsplanets.datasources.database.Database
import com.example.starwarsplanets.features.planets.datasources.database.model.PlanetLocal
import com.example.starwarsplanets.features.planets.datasources.network.PlanetsApi
import com.example.starwarsplanets.features.planets.paging.PlanetsRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlanetsRepository @Inject constructor(
    private val api: PlanetsApi,
    private val database: Database
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getPlanets(): Flow<PagingData<PlanetLocal>> {
        val pagingSourceFactory = { database.planetsDao().getPlanets() }
        return Pager(
            config = PagingConfig(pageSize = 10, initialLoadSize = 60),
            remoteMediator = PlanetsRemoteMediator(
                database,
                api
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getPlanet(id: Long): Flow<PlanetLocal?> = database.planetsDao().getPlanet(id)
}