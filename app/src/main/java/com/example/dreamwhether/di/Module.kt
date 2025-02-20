package com.example.dreamwhether.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.dreamwhether.data.db.DAO
import com.example.dreamwhether.data.db.WhetherDB
import com.example.dreamwhether.data.remoute.repository.WhetherRepositoryImpl
import com.example.dreamwhether.domain.repository.WhetherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun getHttpClient(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    allowSpecialFloatingPointValues = true
                    allowStructuredMapKeys = true
                    useArrayPolymorphism = true
                })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("HTTP call", message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

    @Provides
    @Singleton
    fun getWhetherRepositoryImpl(client: HttpClient): WhetherRepository {
        return WhetherRepositoryImpl(client)
    }

    @Provides
    @Singleton
    fun getDB(@ApplicationContext appContext: Context): WhetherDB {
        return Room.databaseBuilder(
            context = appContext,
            klass = WhetherDB::class.java,
            name = "WhetherDB"
        ).build()
    }

    @Provides
    @Singleton
    fun getDAO(db: WhetherDB): DAO {
        return db.dao()
    }
}