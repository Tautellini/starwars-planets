package com.example.starwarsplanets.features.planets.extensions

import android.net.Uri
import com.example.starwarsplanets.features.planets.datasources.database.model.PlanetLocal

fun PlanetLocal.getIdByUrl(): Int {
    return runCatching { Uri.parse(url).pathSegments.last().toInt() }.getOrNull() ?: 0
}