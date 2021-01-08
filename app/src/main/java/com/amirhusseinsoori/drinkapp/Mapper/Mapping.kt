package com.amirhusseinsoori.drinkapp.Mapper

import com.amirhusseinsoori.drinkapp.api.DrinkResponse
import com.amirhusseinsoori.drinkapp.db.entity.DrinkEntity
import com.amirhusseinsoori.drinkapp.db.entity.FavoritesEntity

class Mapping {


    fun asDrinkList(list:List<FavoritesEntity>): List<DrinkResponse> {
        return list.map {
            DrinkResponse(it.cocktailId, it.image, it.name, it.description, it.hasAlcohol)
        }
    }






    fun asFavoriteEntity(item: DrinkResponse): FavoritesEntity {
        return FavoritesEntity(
            item.cocktailId,
            item.image,
            item.name,
            item.description,
            item.hasAlcohol
        )
    }




}
fun List<DrinkEntity>.getDrinkList(): List<DrinkResponse> = this.map {
    DrinkResponse(it.cocktailId, it.image, it.name, it.description, it.hasAlcohol)
}
fun DrinkResponse.asDrinkEntity(): DrinkEntity =
    DrinkEntity(this.cocktailId, this.image, this.name, this.description, this.hasAlcohol)