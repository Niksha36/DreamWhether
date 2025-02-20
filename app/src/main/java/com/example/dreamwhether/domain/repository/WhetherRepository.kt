package com.example.dreamwhether.domain.repository

import com.example.dreamwhether.data.remoute.dto.WhetherDTO

interface WhetherRepository {
    suspend fun getWhether(location: String, lang: String) : WhetherDTO
}