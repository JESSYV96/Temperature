package fr.jessyv96.tempkotlin.model

import kotlinx.serialization.Serializable

@Serializable
data class Coord(
    val lat: Double,
    val lon: Double
)