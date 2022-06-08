package com.example.starwarsplanets.features.planets.datasources.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.starwarsplanets.features.planets.datasources.database.model.PlanetLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Dao
interface PlanetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlanets(data: List<PlanetLocal>)

    @Query("SELECT * FROM planets")
    fun getPlanets(): PagingSource<Int, PlanetLocal>

    @Query("SELECT * FROM planets WHERE id = :id")
    fun getPlanet(id: Long): Flow<PlanetLocal?>

    @Query("DELETE FROM planets")
    suspend fun clearPlanets()

    @Query("DELETE FROM planets WHERE name = :name")
    fun clearPlanet(name: String)
}