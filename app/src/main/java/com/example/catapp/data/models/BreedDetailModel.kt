package com.example.catapp.data.models

import com.google.gson.annotations.SerializedName

data class BreedDetailModel(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("url")
    val url: String? = ""
)