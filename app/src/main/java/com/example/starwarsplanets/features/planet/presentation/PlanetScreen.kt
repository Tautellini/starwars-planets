package com.example.starwarsplanets.features.planet.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.starwarsplanets.features.planet.PlanetViewModel
import com.example.starwarsplanets.navigation.Route

@Composable
fun PlanetScreen(
    id: Long,
    navController: NavHostController,
    viewModel: PlanetViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getPlanet(id = id)
    }

    val selectedPlanet by viewModel.selectedPlanet.collectAsState()
    val nextPlanet by viewModel.nextPlanet.collectAsState()
    val previousPlanet by viewModel.previousPlanet.collectAsState()

    PlanetContent(
        PlanetContentState.rememberState(
            nextPlanet = nextPlanet,
            previousPlanet = previousPlanet,
            selectedPlanet = selectedPlanet
        ),
        onNextButtonClicked = { navController.navigate(Route.Planet.passId(id + 1)) },
        onPreviousButtonClicked = { navController.navigate(Route.Planet.passId(id - 1)) }
    )
}