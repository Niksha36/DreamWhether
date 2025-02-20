package com.example.dreamwhether.data.remoute.dto

import kotlinx.serialization.Serializable

@Serializable
data class WhetherDTO(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)

