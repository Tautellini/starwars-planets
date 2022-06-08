package com.example.starwarsplanets.datasources.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.starwarsplanets.features.planets.datasources.database.dao.PlanetsDao
import com.example.starwarsplanets.features.planets.datasources.database.dao.PlanetsRemoteKeysDao
import com.example.starwarsplanets.features.planets.datasources.database.model.PlanetLocal
import com.example.starwarsplanets.features.planets.datasources.database.PlanetsTypeConverters
import com.example.starwarsplanets.features.planets.datasources.database.model.PlanetsRemoteKeys

@Database(
    entities = [
        PlanetLocal::class,
        PlanetsRemoteKeys::class
    ],
    version = 1
)
@TypeConverters(
    PlanetsTypeConverters::class
)
abstract class Database : RoomDatabase() {
    abstract fun planetsDao(): PlanetsDao
    abstract fun planetsRemoteKeysDao(): PlanetsRemoteKeysDao
}
