package com.example.starwarsplanets.features.planets.datasources.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarsplanets.features.planets.datasources.database.model.PlanetsRemoteKeys

@Dao
interface PlanetsRemoteKeysDao {

    @Query("SELECT * FROM planets_remote_keys WHERE id = :id")
    suspend fun getPlanetsRemoteKeys(id: Int): PlanetsRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlanetsRemoteKeys(movieRemoteKeys : List<PlanetsRemoteKeys>)

    @Query("DELETE FROM planets_remote_keys")
    suspend fun deleteAllPlanetsRemoteKeys()
}