package com.example.starwarsplanets.features.planet.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.starwarsplanets.features.planet.PlanetViewModel

@Composable
fun PlanetScreen(
    id: Long,
    navController: NavHostController,
    viewModel: PlanetViewModel = hiltViewModel()
) {
    var internalId by remember { mutableStateOf(id) }

    LaunchedEffect(key1 = internalId) {
        viewModel.getPlanet(id = internalId)
    }

    val planet by viewModel.selectedPlanet.collectAsState()

    val previousPlanet by viewModel.previousPlanet.collectAsState()

    val nextPlanet by viewModel.nextPlanet.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
        ) {
            Text("Key: " + planet?.id.toString())
        }

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
        ) {
            Text(
                planet?.name ?: "unbenannt",
                style = MaterialTheme.typography.h3
            )
            Text("Klima: " + planet?.climate.orEmpty())
            Text("Durchmesser: " + planet?.diameter.orEmpty())
            Text("Gravitation: " + planet?.gravity.orEmpty())
            Text("Oribtal-Periode: " + planet?.orbitalPeriod.orEmpty())
            Text("Rotations-Periode: " + planet?.rotationPeriod.orEmpty())
            Text("Bevoelkerung: " + planet?.population.orEmpty())
            Text("Terrain: " + planet?.terrain.orEmpty())
        }

        Row(Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = previousPlanet != null) {
                Button(onClick = {
                    internalId--
                    Log.d("PlanetScreen", "clicked on previous: $previousPlanet")
                }) {
                    Text("Vorheriger Planet")
                }
            }

            Spacer(Modifier.width(10.dp))

            AnimatedVisibility(visible = nextPlanet != null) {
                Button(onClick = {
                    internalId++
                }) {
                    Text("Nächster Planet")
                }
            }
        }
    }
}