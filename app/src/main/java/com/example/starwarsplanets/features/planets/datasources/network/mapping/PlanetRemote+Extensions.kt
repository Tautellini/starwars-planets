package com.example.starwarsplanets.features.planets.datasources.network.mapping

import android.net.Uri
import com.example.starwarsplanets.features.planets.datasources.database.model.PlanetLocal
import com.example.starwarsplanets.features.planets.datasources.network.model.PlanetRemote

fun PlanetRemote.toPlanetLocal(): PlanetLocal = PlanetLocal(
    id = runCatching {
        Uri.parse(url).pathSegments.last().toLong()
    }.getOrDefault(0),
    name = name,
    climate = climate,
    created = created,
    diameter = diameter,
    edited = edited,
    films = films,
    gravity = gravity,
    orbitalPeriod = orbitalPeriod,
    population = population,
    residents = residents,
    rotationPeriod = rotationPeriod,
    surfaceWater = surfaceWater,
    terrain = terrain,
    url = url
)