package com.example.dreamwhether.presentation.screens.week_forecast.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.dreamwhether.data.remoute.dto.Day
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun WeekWhetherItem(
    dayForecast: Day,
    day: String
) {
    val dayOfTheWeek: String = getDayOfWeek(day)
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp).height(70.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(text = day, style = MaterialTheme.typography.bodySmall)
                Text(text = dayOfTheWeek, style = MaterialTheme.typography.titleMedium)
            }

            Row {
                AsyncImage(
                    model = "https:"+dayForecast.condition.icon,
                    modifier = Modifier
                        .fillMaxHeight().aspectRatio(1f),
                    contentDescription = "Состояние погоды в этот день",
                )
                Column(modifier = Modifier.padding(start = 5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Min",  style = MaterialTheme.typography.bodySmall)
                    Text(text = "${dayForecast.mintemp_c.roundToInt()}°",  style = MaterialTheme.typography.titleMedium)
                }
                Column(modifier = Modifier.padding(start = 5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Max",  style = MaterialTheme.typography.bodySmall)
                    Text(text = "${dayForecast.maxtemp_c.roundToInt()}°",  style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

fun getDayOfWeek(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(dateString, formatter)
    val dayOfWeek = date.dayOfWeek
    return dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, Locale("ru"))
}

