package com.example.dreamwhether.presentation.screens.current_whether.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.example.dreamwhether.data.remoute.dto.Condition

@Composable
fun MainInfo(city: String, currentTemp: Int, feelsLike: Int, condition: Condition) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = city,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 25.dp)
        )
        Row(
            Modifier
                .height(IntrinsicSize.Max)
                .padding(bottom = 25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$currentTemp°C",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(end = 15.dp)
            )
            AsyncImage(
                model = "https:" + condition.icon,
                contentDescription = condition.text,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)

            )
        }
        Text(
            text = condition.text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        Text(
            text = "Ощущается как ${feelsLike}°C",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
