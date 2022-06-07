package com.example.starwarsplanets.features.planets.datasources.network

import com.example.starwarsplanets.features.planets.datasources.database.PlanetLocal

fun PlanetRemote.toPlanetLocal(): PlanetLocal = PlanetLocal(
    climate = climate,
    created = created,
    diameter = diameter,
    edited = edited,
    films = films,
    gravity = gravity,
    name = name,
    orbitalPeriod = orbitalPeriod,
    population = population,
    residents = residents,
    rotationPeriod = rotationPeriod,
    surfaceWater = surfaceWater,
    terrain = terrain,
    url = url
)