package com.amirhusseinsoori.drinkapp.api

import com.google.gson.annotations.SerializedName

class DrinkList {
    @SerializedName("drinks")
    val cocktailList: List<DrinkResponse> = listOf()
}