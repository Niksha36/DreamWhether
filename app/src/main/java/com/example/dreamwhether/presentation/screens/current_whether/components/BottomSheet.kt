package com.example.dreamwhether.presentation.screens.current_whether.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dreamwhether.data.remoute.dto.Current
import com.example.dreamwhether.presentation.screens.current_whether.SpecCardInfo
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun BottomSheet(currentWhether: Current) {
    val currentWhetherSpecs = listOf(
        SpecCardInfo(
            title = "Cкорость ветра",
            value = currentWhether.wind_kph.toString(),
            units = "км/ч"
        ),
        SpecCardInfo(
            title = "Направление ветра",
            value = "${currentWhether.wind_degree}° ${currentWhether.wind_dir} ",
        ),
        SpecCardInfo(
            title = "Атмосферное давление",
            value = currentWhether.pressure_in.toString(),
            units = "дюйм"
        ),
        SpecCardInfo(
            title = "Влажность",
            value = currentWhether.humidity.toString(),
            units = "%"
        ),
        SpecCardInfo(
            title = "Точка росы",
            value = currentWhether.dewpoint_c.toString(),
            units = "°C"
        ),
        SpecCardInfo(
            title = "Облачность",
            value = currentWhether.cloud.toString(),
            units = "%"
        ),
        SpecCardInfo(
            title = "Порывы ветра",
            value = currentWhether.gust_kph.toString(),
            units = "км/ч"
        ),
        SpecCardInfo(
            title = "Качество воздуха",
            value = currentWhether.air_quality.usEpaIndex.toString(),
            units = "US EPA INDEX"
        )
    )

    val sheetState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetContent = {
            LazyColumn(
                modifier = Modifier.padding( bottom = 20.dp, start = 13.dp, end = 13.dp)
            ) {
                items(currentWhetherSpecs.chunked(2)) { rowItems ->
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
                        rowItems.forEach { spec ->
                            WhetherSpecCard(
                                title = spec.title,
                                value = spec.value,
                                units = spec.units,
                            )
                        }
                    }
                }
            }
        },
        sheetPeekHeight = 220.dp
    ) {}
}
