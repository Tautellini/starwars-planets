package com.example.starwarsplanets.features.planet.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.starwarsplanets.features.planets.datasources.database.model.PlanetLocal
import com.example.starwarsplanets.shared.ui.theme.StarWarsPlanetsTheme

@Composable
fun PlanetContent(
    state: PlanetContentState,
    onNextButtonClicked: () -> Unit,
    onPreviousButtonClicked: () -> Unit
) {
    Layout {
        KeyInformation(state)
        PlanetInfo(state)
        Buttons(state, onPreviousButtonClicked, onNextButtonClicked)
    }
}

@Composable
fun Layout(content: @Composable ColumnScope.() -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        content()
    }
}

@Composable
fun ColumnScope.Buttons(
    state: PlanetContentState,
    onPreviousButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit
) {
    Row(Modifier.fillMaxSize()) {
        if (state.isPreviousPlanetButtonVisible) {
            Button(onClick = {
                onPreviousButtonClicked()
            }) {
                Text("Vorheriger Planet")
            }
        }

        Spacer(Modifier.width(10.dp))

        if (state.isNextPlanetButtonVisible) {
            Button(onClick = {
                onNextButtonClicked()
            }) {
                Text("Nächster Planet")
            }
        }
    }
}

@Composable
fun ColumnScope.KeyInformation(state: PlanetContentState) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
    ) {
        Text(state.planetKey)
    }
}

@Composable
fun ColumnScope.PlanetInfo(state: PlanetContentState) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
    ) {
        Text(
            state.selectedPlanetText,
            style = MaterialTheme.typography.h3
        )
        Text(state.climateText)
        Text(state.diameterText)
        Text(state.gravityText)
        Text(state.orbitalPeriodText)
        Text(state.rotationPeriodText)
        Text(state.populationText)
        Text(state.terrainText)
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF333333
)
@Composable
private fun InteractivePreview() {
    StarWarsPlanetsTheme {
        var selectedId by remember { mutableStateOf(1) }

        val mockedList = listOf(
            PlanetLocal(id = 1, name = "Test Planet 1"),
            PlanetLocal(id = 2, name = "Test Planet 2"),
            PlanetLocal(id = 3, name = "Test Planet 3"),
            PlanetLocal(id = 4, name = "Test Planet 4"),
        )

        val selectedPlanet = mockedList[selectedId]
        val previousPlanet = if (0 <= selectedId - 1) mockedList[selectedId - 1] else null
        val nextPlanet = if (mockedList.size > selectedId + 1) mockedList[selectedId + 1] else null

        val state = PlanetContentState.rememberState(
            nextPlanet = nextPlanet,
            previousPlanet = previousPlanet,
            selectedPlanet = selectedPlanet
        )

        PlanetContent(state = state,
            onNextButtonClicked = { selectedId++ },
            onPreviousButtonClicked = { selectedId-- }
        )
    }
}