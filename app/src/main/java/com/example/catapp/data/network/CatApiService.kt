package com.example.catapp.data.network

import com.example.catapp.data.BreedDetailModel
import com.example.catapp.data.CatBreedDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {

    @GET("breeds")
    suspend fun getCatBreed(): List<CatBreedDataModel>

    @GET("images/search?breed_ids=")
    suspend fun getBreedDetails(
        @Query("i") id: String
    ): List<BreedDetailModel>
}