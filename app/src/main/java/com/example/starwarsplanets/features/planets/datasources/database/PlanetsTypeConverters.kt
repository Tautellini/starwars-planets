package com.example.starwarsplanets.features.planets.datasources.database

import androidx.room.TypeConverter
import com.example.starwarsplanets.features.planets.datasources.database.model.PlanetLocal
import com.example.starwarsplanets.shared.services.json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

object PlanetsTypeConverters {

    @TypeConverter
    fun fromList(value: List<String>): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun toList(value: String): List<String> {
        return json.decodeFromString(value)
    }

    // region Planets Feature

    @TypeConverter
    fun fromPlanets(value: List<PlanetLocal>): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun toPlanets(value: String): List<PlanetLocal> {
        return json.decodeFromString(value)
    }


    // endregion

}