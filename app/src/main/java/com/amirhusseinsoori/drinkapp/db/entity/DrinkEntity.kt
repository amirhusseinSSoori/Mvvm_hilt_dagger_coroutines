package com.amirhusseinsoori.drinkapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drinkTable")
data class DrinkEntity(
    @PrimaryKey
    val cocktailId: String,
    @ColumnInfo(name = "trago_imagen")
    val image: String = "",
    @ColumnInfo(name = "trago_nombre")
    val name: String = "",
    @ColumnInfo(name = "trago_descripcion")
    val description: String = "",
    @ColumnInfo(name = "trago_has_alcohol")
    val hasAlcohol: String = "Non_Alcoholic"
)
