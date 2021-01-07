package com.amirhusseinsoori.drinkapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amirhusseinsoori.drinkapp.db.entity.DrinkEntity
import com.amirhusseinsoori.drinkapp.db.entity.FavoritesEntity

@Database(entities = [FavoritesEntity::class, DrinkEntity::class],version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cocktailDao(): DrinkDao
}