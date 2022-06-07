package com.example.starwarsplanets.features.planets.domain

interface PlanetsRepository {
    fun getAllPlanets(): List<Planet>
//    fun getPlanet(id: Int): Planet
}