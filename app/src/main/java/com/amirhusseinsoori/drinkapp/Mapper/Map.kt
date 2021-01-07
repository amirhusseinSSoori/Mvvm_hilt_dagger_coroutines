package com.amirhusseinsoori.drinkapp.Mapper

import com.amirhusseinsoori.drinkapp.api.ResponseDrink
import com.amirhusseinsoori.drinkapp.db.entity.DrinkEntity
import com.amirhusseinsoori.drinkapp.db.entity.FavoritesEntity

class Map {
    fun List<FavoritesEntity>.asDrinkList(): List<ResponseDrink> = this.map {
        ResponseDrink(it.cocktailId, it.image, it.name, it.description, it.hasAlcohol)
    }

    fun List<DrinkEntity>.getDrinkList(): List<ResponseDrink> = this.map {
        ResponseDrink(it.cocktailId, it.image, it.name, it.description, it.hasAlcohol)
    }

    fun ResponseDrink.asDrinkEntity(): DrinkEntity =
       DrinkEntity(this.cocktailId, this.image, this.name, this.description, this.hasAlcohol)

    fun ResponseDrink.asFavoriteEntity(): FavoritesEntity =
        FavoritesEntity(this.cocktailId, this.image, this.name, this.description, this.hasAlcohol)
}