package com.example.starwarsplanets.features.planets

import androidx.lifecycle.ViewModel
import com.example.starwarsplanets.features.planets.usecases.PlanetsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
    useCases: PlanetsUseCases,
) : ViewModel() {
    val getAllPlanets = useCases.getAllPlanets()

}