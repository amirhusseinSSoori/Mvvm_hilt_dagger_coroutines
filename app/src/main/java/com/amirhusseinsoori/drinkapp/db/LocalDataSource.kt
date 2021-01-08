package com.amirhusseinsoori.drinkapp.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.amirhusseinsoori.drinkapp.Mapper.Mapping
import com.amirhusseinsoori.drinkapp.Mapper.getDrinkList
import com.amirhusseinsoori.drinkapp.api.DrinkResponse
import com.amirhusseinsoori.drinkapp.core.Resource
import com.amirhusseinsoori.drinkapp.db.entity.DrinkEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LocalDataSource @Inject constructor(private val cocktailDao: DrinkDao) {
    suspend fun saveFavoriteCocktail( cocktail: DrinkResponse) {
        return cocktailDao.saveFavoriteCocktail(Mapping().asFavoriteEntity(cocktail))
    }

    fun getFavoritesCocktails(): LiveData<List<DrinkResponse>> {
        return cocktailDao.getAllFavoriteDrinksWithChanges().map { Mapping().asDrinkList(it)}
    }

    suspend fun deleteCocktail(cocktail: DrinkResponse) {
        return cocktailDao.deleteFavoriteCoktail(Mapping().asFavoriteEntity(cocktail))
    }

    suspend fun saveCocktail(cocktail: DrinkEntity) {
        cocktailDao.saveCocktail(cocktail)
    }

    suspend fun getCachedCocktails(cocktailName: String): Resource<List<DrinkResponse>> {
        return Resource.Success(cocktailDao.getCocktails(cocktailName).getDrinkList())
    }

    suspend fun isCocktailFavorite(cocktail: DrinkResponse): Boolean {
        return cocktailDao.getCocktailById(cocktail.cocktailId) != null
    }


}