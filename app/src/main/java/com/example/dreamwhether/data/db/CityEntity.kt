package com.example.dreamwhether.data.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
indices = [Index(value = ["city"], unique = true)]
)
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val city: String,
    val isSelected: Boolean = false
)
