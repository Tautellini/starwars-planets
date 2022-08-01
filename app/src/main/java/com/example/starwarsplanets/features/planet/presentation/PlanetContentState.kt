package com.example.starwarsplanets.features.planet.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.starwarsplanets.features.planet.presentation.PlanetContentState.Companion.rememberState
import com.example.starwarsplanets.features.planets.datasources.database.model.PlanetLocal

/**
 * use [rememberState] to construct the state.
 */
class PlanetContentState(
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
        private val Saver: Saver<PlanetContentState, Any> = listSaver(
            save = {
                listOf(
                    it.selectedPlanet?.climate.toString(),
                    it.previousPlanet?.climate.toString(),
                    it.nextPlanet?.climate.toString()
                )
            },
            restore = {
                PlanetContentState(
                    selectedPlanet = PlanetLocal(id = 0, climate = it[0]),
                    previousPlanet = PlanetLocal(id = 2, climate = it[1]),
                    nextPlanet = PlanetLocal(id = 1, climate = it[2])
                )
            }
        )

        @Composable
        fun rememberState(
            selectedPlanet: PlanetLocal? = null,
            previousPlanet: PlanetLocal? = null,
            nextPlanet: PlanetLocal? = null
        ) = rememberSaveable(selectedPlanet, previousPlanet, nextPlanet, stateSaver = Saver) {
            mutableStateOf(
                PlanetContentState(
                    selectedPlanet = selectedPlanet,
                    previousPlanet = previousPlanet,
                    nextPlanet = nextPlanet
                )
            )
        }
    }
}