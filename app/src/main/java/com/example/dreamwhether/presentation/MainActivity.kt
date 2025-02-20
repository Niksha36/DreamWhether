package com.example.dreamwhether.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.DreamWhetherTheme
import com.example.dreamwhether.presentation.screens.current_whether.CurrentWhetherScreen
import com.example.dreamwhether.presentation.screens.locations.LocationsScreen
import com.example.dreamwhether.presentation.screens.week_forecast.WeekForecastScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DreamWhetherTheme(dynamicColor = false) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Pager()
                }
            }
        }
    }
}
@ExperimentalMaterial3Api
@Composable
fun Pager() {
    val tabTitles = listOf("Сегодня", "Неделя", "Мои города")
    val pagerState = rememberPagerState(
        pageCount = { tabTitles.size }
    )
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(top = 20.dp)) {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(title) }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            when (page) {
                0 -> CurrentWhetherScreen()
                1 ->  WeekForecastScreen()
                2 -> LocationsScreen()
            }
        }
    }
}
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun Preview() {
    CurrentWhetherScreen()
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun SimpleTabComponentPreview() {
    Pager()
}