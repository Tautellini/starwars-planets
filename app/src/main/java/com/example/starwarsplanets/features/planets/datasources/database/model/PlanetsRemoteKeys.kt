package com.example.starwarsplanets.features.planets.datasources.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planets_remote_keys")
data class PlanetsRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?,
)