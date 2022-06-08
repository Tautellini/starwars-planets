package com.example.starwarsplanets.shared.services

import kotlinx.serialization.json.Json

val json: Json by lazy {
    Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }
}