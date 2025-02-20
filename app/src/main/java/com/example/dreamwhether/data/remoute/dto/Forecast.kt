package com.example.dreamwhether.data.remoute.dto
import android.util.Log
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Serializable
data class Forecast(
    val forecastday: List<Forecastday>
)

fun Forecast.getNext24Hours(): List<Hour> {
    val currentDateTime = LocalDateTime.now()
    Log.i("DATE", currentDateTime.toString())
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val endDateTime = currentDateTime.plusHours(23)
    Log.i("END_DATE", endDateTime.toString())

    val next24Hours = mutableListOf<Hour>()

    this.forecastday.forEach { forecastday ->
        forecastday.hour.filter { hour ->
            val hourDateTime = LocalDateTime.parse(hour.time, formatter)
            val current = currentDateTime.withMinute(0).withSecond(0).withNano(0)
            (hourDateTime.isAfter(currentDateTime) || hourDateTime.isEqual(current))  && hourDateTime.isBefore(endDateTime)
        }.let { next24Hours.addAll(it) }
    }

    return next24Hours
}