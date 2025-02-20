package com.example.dreamwhether.presentation.screens.locations.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlin.math.roundToInt

@Composable
fun CityItem(cityName: String, imgURL:String, currentTemp: Double, isSelected:Boolean, onClick: (String) -> Unit) {
    val cardColor = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.secondaryContainer
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp).height(80.dp).clickable { onClick(cityName) },
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Row(
            modifier = Modifier.padding(15.dp).height(80.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(cityName, style = MaterialTheme.typography.displaySmall)

            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    contentDescription="текущая погода",
                    model = "https:$imgURL",
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                )
                Text(text = currentTemp.roundToInt().toString()+"°",  style = MaterialTheme.typography.displaySmall)
            }
        }
    }

}