package com.example.dreamwhether.data.remoute.repository

import android.util.Log
import com.example.dreamwhether.common.Constants
import com.example.dreamwhether.common.Constants.URL
import com.example.dreamwhether.data.remoute.dto.WhetherDTO
import com.example.dreamwhether.domain.repository.WhetherRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class WhetherRepositoryImpl @Inject constructor(val client: HttpClient) : WhetherRepository {
    override suspend fun getWhether(location: String, lang: String): WhetherDTO {
        val response = client.get(urlString = URL) {
            parameter("key", Constants.API_KEY)
            parameter("q", location)
            parameter("aqi", "yes")
            parameter("days", "7")
            parameter("lang", lang)
        }
        return response.body<WhetherDTO>()
    }
}
