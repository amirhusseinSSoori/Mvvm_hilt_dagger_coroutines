package com.amirhusseinsoori.drinkapp.resposiory

import androidx.lifecycle.LiveData
import com.amirhusseinsoori.drinkapp.Mapper.asDrinkEntity
import com.amirhusseinsoori.drinkapp.api.DrinkResponse
import com.amirhusseinsoori.drinkapp.api.NetworkDataSource
import com.amirhusseinsoori.drinkapp.core.Resource
import com.amirhusseinsoori.drinkapp.db.LocalDataSource
import com.amirhusseinsoori.drinkapp.db.entity.DrinkEntity

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class DefaultCocktailRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) : DrinkRepository {

    @InternalCoroutinesApi
    override suspend fun getCocktailByName(cocktailName: String): Flow<Resource<List<DrinkResponse>>> {
        return callbackFlow {

            offer(getCachedCocktails(cocktailName))

            networkDataSource.getDrinkByName(cocktailName).collect {
                when (it) {
                    is Resource.Success -> {
                        for (cocktail in it.data) {
                            saveCocktail(cocktail.asDrinkEntity())
                        }
                        offer(getCachedCocktails(cocktailName))
                    }
                    is Resource.Failure -> {
                        offer(getCachedCocktails(cocktailName))
                    }
                }
            }
            awaitClose { cancel() }
        }
    }


    override suspend fun saveFavoriteCocktail(cocktail: DrinkResponse) {
        localDataSource.saveFavoriteCocktail(cocktail)
    }

    override suspend fun isCocktailFavorite(cocktail: DrinkResponse): Boolean =
        localDataSource.isCocktailFavorite(cocktail)



    override suspend fun saveCocktail(cocktail: DrinkEntity) {
        localDataSource.saveCocktail(cocktail)
    }

    override fun getFavoritesCocktails(): LiveData<List<DrinkResponse>> {
        return localDataSource.getFavoritesCocktails()
    }

    override suspend fun deleteFavoriteCocktail(cocktail: DrinkResponse) {
        localDataSource.deleteCocktail(cocktail)
    }

    override suspend fun getCachedCocktails(cocktailName: String): Resource<List<DrinkResponse>> {
        return localDataSource.getCachedCocktails(cocktailName)
    }


}