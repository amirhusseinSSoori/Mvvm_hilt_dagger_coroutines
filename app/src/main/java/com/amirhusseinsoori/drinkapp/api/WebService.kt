package com.amirhusseinsoori.drinkapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("search.php")
    suspend fun getDrinkByName(@Query(value = "s") tragoName: String): DrinkList?
}