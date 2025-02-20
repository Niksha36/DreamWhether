package com.example.dreamwhether.data.remoute.dto
import kotlinx.serialization.Serializable

@Serializable
data class AirQuality(
    val co: Double,
//    val gbDefraIndex: Double = 0.0,
    val no2: Double,
    val o3: Double,
    val pm10: Double,
    val pm2_5: Double,
    val so2: Double,
    val usEpaIndex: Double = 0.0
)