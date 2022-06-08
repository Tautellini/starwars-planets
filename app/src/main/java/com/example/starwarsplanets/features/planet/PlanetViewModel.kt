package com.example.starwarsplanets.features.planet

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsplanets.features.planets.datasources.database.model.PlanetLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetViewModel @Inject constructor(
    private val useCases: PlanetUseCases,
) : ViewModel() {

    private val _selectedPlanet: MutableStateFlow<PlanetLocal?> = MutableStateFlow(null)
    val selectedPlanet: StateFlow<PlanetLocal?> = _selectedPlanet

    private val _previousPlanet: MutableStateFlow<PlanetLocal?> = MutableStateFlow(null)
    val previousPlanet: StateFlow<PlanetLocal?> = _previousPlanet

    private val _nextPlanet: MutableStateFlow<PlanetLocal?> = MutableStateFlow(null)
    val nextPlanet: StateFlow<PlanetLocal?> = _nextPlanet


    fun getPlanet(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getPlanet(id = id).collect {
                _selectedPlanet.value = it
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getPlanet(id = id - 1).collect {
                Log.d("PlanetViewModel", "prev planet: $it")
                _previousPlanet.value = it
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getPlanet(id = id + 1).collect {
                _nextPlanet.value = it
            }
        }
    }
}