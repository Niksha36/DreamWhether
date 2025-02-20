package com.example.dreamwhether.data.remoute.dto
import android.util.Log
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Serializable
data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
)

//fun Forecastday.getNext24Hours(): List<Hour> {
//    val currentDateTime = LocalDateTime.now()
//    Log.i("DATE", currentDateTime.toString())
//    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
//    val endDateTime = currentDateTime.plusHours(23)
//    Log.i("END_DATE", endDateTime.toString())
//    return this.hour
//        .filter { hour ->
//            val hourDateTime = LocalDateTime.parse(hour.time, formatter)
//            hourDateTime.isAfter(currentDateTime) && hourDateTime.isBefore(endDateTime)
//        }
//}