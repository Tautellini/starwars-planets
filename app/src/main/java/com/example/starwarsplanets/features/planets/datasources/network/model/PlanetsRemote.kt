package com.example.starwarsplanets.features.planets.datasources.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanetsRemote(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("next")
    val next: String? = null,
    @SerialName("previous")
    val previous: String? = null,
    @SerialName("results")
    val results: List<PlanetRemote>? = null
)