package com.hayatibahar.simpleandyummy.core.network.dto


import com.google.gson.annotations.SerializedName

data class RecipesResponse(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val results: List<Result?>?,
    @SerializedName("totalResults")
    val totalResults: Int?
)