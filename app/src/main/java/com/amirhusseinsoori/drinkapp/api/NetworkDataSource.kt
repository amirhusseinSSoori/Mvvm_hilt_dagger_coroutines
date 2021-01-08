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
    suspend fun getDrinkByName(cocktailName: String): Flow<Resource<List<DrinkResponse>>> =
        callbackFlow {
            offer(
                Resource.Success(
                    webService.getDrinkByName(cocktailName)?.cocktailList ?: listOf()
                )
            )
            awaitClose { close() }
        }
}