package com.amirhusseinsoori.drinkapp.resposiory

import androidx.lifecycle.LiveData
import com.amirhusseinsoori.drinkapp.api.DrinkResponse
import com.amirhusseinsoori.drinkapp.core.Resource
import com.amirhusseinsoori.drinkapp.db.entity.DrinkEntity
import kotlinx.coroutines.flow.Flow

interface DrinkRepository {

    suspend fun getCocktailByName(cocktailName: String): Flow<Resource<List<DrinkResponse>>>
    suspend fun saveFavoriteCocktail(cocktail: DrinkResponse)
    suspend fun isCocktailFavorite(cocktail: DrinkResponse): Boolean
    suspend fun getCachedCocktails(cocktailName: String): Resource<List<DrinkResponse>>
    suspend fun saveCocktail(cocktail: DrinkEntity)
    fun getFavoritesCocktails(): LiveData<List<DrinkResponse>>
    suspend fun deleteFavoriteCocktail(cocktail: DrinkResponse)
}