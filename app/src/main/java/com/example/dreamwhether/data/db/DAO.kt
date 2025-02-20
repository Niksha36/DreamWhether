package com.example.dreamwhether.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {
    @Upsert
    suspend fun insertCity(city: CityEntity)
    @Query("UPDATE CityEntity SET isSelected = CASE WHEN city = :city THEN 1 ELSE 0 END")
    suspend fun makeCityCurrent(city: String)
    @Delete
    suspend fun deleteCity(city: CityEntity)
    @Query("SELECT city FROM CityEntity")
    fun getAll(): Flow<List<String>>
    @Query("SELECT city from CityEntity c where c.isSelected = 1")
    fun getSelectedCity(): Flow<String>
}