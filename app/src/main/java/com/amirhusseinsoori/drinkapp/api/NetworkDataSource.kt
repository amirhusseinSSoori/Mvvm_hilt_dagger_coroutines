package com.amirhusseinsoori.drinkapp.api

import com.amirhusseinsoori.drinkapp.core.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NetworkDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getCocktailByName(cocktailName: String): Flow<Resource<List<ResponseDrink>>> =
        callbackFlow {
            offer(
                Resource.Success(
                    webService.getCocktailByName(cocktailName)?.cocktailList ?: listOf()
                )
            )
            awaitClose { close() }
        }
}