package com.example.dreamwhether.presentation.screens.current_whether.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WhetherSpecCard(title: String, value: String, units: String) {
    Card(elevation = CardDefaults.elevatedCardElevation(5.dp)){
        Column(Modifier.padding(10.dp).widthIn(min = 120.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.height(5.dp))
            Text(text = value, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(5.dp))
            Text(text = units, style = MaterialTheme.typography.titleMedium)
        }
    }
}