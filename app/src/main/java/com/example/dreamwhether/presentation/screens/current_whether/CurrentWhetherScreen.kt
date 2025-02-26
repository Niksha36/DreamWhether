package com.example.dreamwhether.presentation.screens.current_whether

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dreamwhether.data.remoute.dto.getNext24Hours
import com.example.dreamwhether.presentation.screens.current_whether.components.BottomSheet
import com.example.dreamwhether.presentation.screens.current_whether.components.HourForecastItem
import com.example.dreamwhether.presentation.screens.current_whether.components.MainInfo
import kotlin.math.roundToInt

@Composable
@ExperimentalMaterial3Api
fun CurrentWhetherScreen(
    viewModel: ViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp)
    ) {
        state.data?.let {
            val currentWhether = it.current
            val city = it.location.name
            val currentTemp: Int =currentWhether.temp_c.roundToInt()
            val feelsLike: Int = currentWhether.feelslike_c.roundToInt()
            val currentCondition = currentWhether.condition
            val currentHourForecast = it.forecast.getNext24Hours()
            Log.i("currentHourForecast", currentHourForecast.toString())
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainInfo(city, currentTemp, feelsLike, currentCondition)
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(contentPadding = PaddingValues(10.dp)) {
                    items(currentHourForecast) {
                        val time = it.time.split(" ")[1]
                        val condition = it.condition
                        val hourTemp = it.temp_c.roundToInt()
                        HourForecastItem(time, condition, hourTemp)
                    }
                }

            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .fillMaxWidth()
            ) {
                BottomSheet(currentWhether)
            }
        }
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

    }
}
