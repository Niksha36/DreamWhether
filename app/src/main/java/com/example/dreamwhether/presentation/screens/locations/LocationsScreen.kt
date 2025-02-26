package com.example.dreamwhether.presentation.screens.locations

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dreamwhether.data.db.CityEntity
import com.example.dreamwhether.presentation.screens.current_whether.ViewModel
import com.example.dreamwhether.presentation.screens.locations.components.AddCityDialog
import com.example.dreamwhether.presentation.screens.locations.components.CityItem
import com.example.dreamwhether.presentation.screens.locations.components.MakeCityCurrentDialog

@Composable
fun LocationsScreen() {
    val viewModel = hiltViewModel<ViewModel>()
    viewModel.getWhetherForCities()
    val citiesForecasts = viewModel.citiesForecast.value
    val currentCity by viewModel.currentCity
    var showAllLocationDialog by remember { mutableStateOf(false) }
    val locationCurrentDialog = viewModel.showMakeLocationCurrentDialog.value
    var selectedCity by remember { mutableStateOf("") }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp)) {
        LazyColumn {
            items(citiesForecasts) {
                it.data?.let { CityWhether ->
                        val cityName: String = CityWhether.location.name
                        val iconURL: String = CityWhether.current.condition.icon
                        val temp: Double = CityWhether.current.temp_c
                        val isCurrentCity = currentCity.lowercase() == cityName.lowercase()
                    CityItem(cityName, iconURL, temp, isCurrentCity){
                        if(!isCurrentCity){
                            selectedCity = it
                            viewModel.setShowMakeLocationCurrentDialog(true)
                        }

                    }
                }

            }
        }
        FloatingActionButton(
            onClick = {
                showAllLocationDialog = true
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 80.dp, end = 20.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }
    if (showAllLocationDialog) {
        AddCityDialog(
            onDismiss = { showAllLocationDialog = false },
            onAdd = { cityName ->
                viewModel.addCity(CityEntity(city = cityName))
                showAllLocationDialog = false
            }
        )
    }
    if (locationCurrentDialog) {
        MakeCityCurrentDialog(
            onDismiss = { viewModel.setShowMakeLocationCurrentDialog(false) },
            onConfirm = {
                viewModel.makeCityCurrent(selectedCity)
                Log.e("SelCity", selectedCity)
                viewModel.setShowMakeLocationCurrentDialog(false)
            }
        )
    }
}