package com.example.dreamwhether.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CityEntity::class], version = 1)
abstract class WhetherDB : RoomDatabase() {
    abstract fun dao(): DAO
}