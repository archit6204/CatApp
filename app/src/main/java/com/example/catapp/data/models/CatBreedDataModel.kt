package com.example.catapp.data.models

import com.google.gson.annotations.SerializedName

data class CatBreedDataModel(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("origin")
    val origin: String? = "",
    @SerializedName("life_span")
    val lifeSpan: String? = ""
)