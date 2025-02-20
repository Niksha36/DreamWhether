package com.example.dreamwhether.presentation.screens.current_whether

import com.example.dreamwhether.data.remoute.dto.WhetherDTO


data class CurrentWhetherState(
    val error: String = "",
    val data: WhetherDTO? = null,
    val isLoading: Boolean = false
)
