package com.example.starwarsplanets.features.planets.datasources.database.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "planets")
@Serializable
data class PlanetLocal(
    @PrimaryKey(autoGenerate = false)
    var id: Long = 0,
    @SerialName("name")
    val name: String? = null,
    @SerialName("climate")
    val climate: String? = null,
    @SerialName("created")
    val created: String? = null,
    @SerialName("diameter")
    val diameter: String? = null,
    @SerialName("edited")
    val edited: String? = null,
    @SerialName("films")
    val films: List<String>? = null,
    @SerialName("gravity")
    val gravity: String? = null,
    @SerialName("orbital_period")
    val orbitalPeriod: String? = null,
    @SerialName("population")
    val population: String? = null,
    @SerialName("residents")
    val residents: List<String>? = null,
    @SerialName("rotation_period")
    val rotationPeriod: String? = null,
    @SerialName("surface_water")
    val surfaceWater: String? = null,
    @SerialName("terrain")
    val terrain: String? = null,
    @SerialName("url")
    val url: String? = null
)