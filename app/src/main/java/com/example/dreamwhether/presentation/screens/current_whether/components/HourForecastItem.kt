package com.example.dreamwhether.presentation.screens.current_whether.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.dreamwhether.data.remoute.dto.Condition

@Composable
fun HourForecastItem(time: String, condition: Condition, temp: Int) {
    Card( modifier = Modifier.padding(end=7.dp)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
            Text(
                text = time,
                modifier = Modifier.padding(bottom = 10.dp),
                style = MaterialTheme.typography.bodySmall
            )
            AsyncImage(
                model = "https:" + condition.icon,
                contentDescription = condition.text,
                modifier = Modifier
                    .height(50.dp)
                    .padding(bottom = 10.dp)
            )
            Text(
                text = temp.toString(),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}