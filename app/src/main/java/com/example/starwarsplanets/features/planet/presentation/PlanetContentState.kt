package com.example.starwarsplanets.features.planet.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.starwarsplanets.features.planet.presentation.PlanetContentState.Companion.rememberState
import com.example.starwarsplanets.features.planets.datasources.database.model.PlanetLocal

/**
 * use [rememberState] to construct the state.
 */
class PlanetContentState private constructor(
    private val selectedPlanet: PlanetLocal?,
    private val previousPlanet: PlanetLocal?,
    private val nextPlanet: PlanetLocal?
) {
    val planetKey @Composable get() = "Key: " + selectedPlanet?.id.toString()

    val selectedPlanetText @Composable get() = selectedPlanet?.name ?: "unbenannt"

    val climateText @Composable get() = "Klima: " + selectedPlanet?.climate.orEmpty()
    val diameterText @Composable get() = "Durchmesser: " + selectedPlanet?.diameter.orEmpty()
    val gravityText @Composable get() = "Gravitation: " + selectedPlanet?.gravity.orEmpty()
    val orbitalPeriodText @Composable get() = "Oribtal-Periode: " + selectedPlanet?.orbitalPeriod.orEmpty()
    val rotationPeriodText @Composable get() = "Rotations-Periode: " + selectedPlanet?.rotationPeriod.orEmpty()
    val populationText @Composable get() = "Bevoelkerung: " + selectedPlanet?.population.orEmpty()
    val terrainText @Composable get() = "Terrain: " + selectedPlanet?.terrain.orEmpty()

    val isPreviousPlanetButtonVisible @Composable get() = previousPlanet != null
    val isNextPlanetButtonVisible @Composable get() = nextPlanet != null

    companion object {
        @Composable
        fun rememberState(
            selectedPlanet: PlanetLocal? = null,
            previousPlanet: PlanetLocal? = null,
            nextPlanet: PlanetLocal? = null
        ) = remember(selectedPlanet, previousPlanet, nextPlanet) {
            PlanetContentState(
                selectedPlanet = selectedPlanet,
                previousPlanet = previousPlanet,
                nextPlanet = nextPlanet
            )
        }
    }
}