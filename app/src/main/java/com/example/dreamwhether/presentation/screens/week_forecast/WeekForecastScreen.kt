package com.example.dreamwhether.presentation.screens.week_forecast

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dreamwhether.presentation.screens.current_whether.ViewModel
import com.example.dreamwhether.presentation.screens.week_forecast.components.WeekWhetherItem

@Composable
fun WeekForecastScreen(viewModel: ViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier.fillMaxSize().padding(top=15.dp)
    ) {
        state.data?.let{
            val forecastList = it.forecast.forecastday
            LazyColumn() {
                items(forecastList){
                    val day = it.date
                    val dayForecast = it.day
                    WeekWhetherItem(dayForecast, day)
                }
            }
        }

    }
}