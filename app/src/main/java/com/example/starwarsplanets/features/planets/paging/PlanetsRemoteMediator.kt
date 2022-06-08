package com.example.starwarsplanets.features.planets.paging

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.starwarsplanets.datasources.database.Database
import com.example.starwarsplanets.features.planets.datasources.database.model.PlanetLocal
import com.example.starwarsplanets.features.planets.datasources.database.model.PlanetsRemoteKeys
import com.example.starwarsplanets.features.planets.datasources.network.PlanetsApi
import com.example.starwarsplanets.features.planets.datasources.network.mapping.toPlanetLocal
import com.example.starwarsplanets.features.planets.extensions.getIdByUrl
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PlanetsRemoteMediator @Inject constructor(
    private val database: Database,
    private val planetsApi: PlanetsApi
) : RemoteMediator<Int, PlanetLocal>() {

    private val planetsDao = database.planetsDao()
    private val planetsRemoteKeysDao = database.planetsRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PlanetLocal>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1 // todo check for closest item
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val response = planetsApi.getPlanets(page)
            var endOfPaginationReached = false
            if (response.isSuccessful) {
                val responseData = response.body()
                endOfPaginationReached = responseData == null
                responseData?.let {
                    database.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            planetsDao.clearPlanets()
                            planetsRemoteKeysDao.deleteAllPlanetsRemoteKeys()
                        }

                        val nextPage: Int = page + 1
                        val prevPage: Int? = if (page <= 1) null else page - 1

                        val keys = responseData.results?.map { planet ->
                            PlanetsRemoteKeys(
                                id = runCatching {
                                    Uri.parse(planet.url).pathSegments.last().toInt()
                                }.getOrDefault(0),
                                prevPage = prevPage,
                                nextPage = nextPage,
                                lastUpdated = System.currentTimeMillis()
                            )
                        }.orEmpty()
                        planetsRemoteKeysDao.insertAllPlanetsRemoteKeys(movieRemoteKeys = keys)
                        planetsDao.insertPlanets(responseData.results?.map {
                            it.toPlanetLocal()
                        }.orEmpty())
                    }
                }

            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, PlanetLocal>,
    ): PlanetsRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { planet ->
                planetsRemoteKeysDao.getPlanetsRemoteKeys(planet.getIdByUrl())
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PlanetLocal>,
    ): PlanetsRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { planet ->
                planetsRemoteKeysDao.getPlanetsRemoteKeys(planet.getIdByUrl())
            }
    }
}