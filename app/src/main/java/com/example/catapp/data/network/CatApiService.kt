package com.example.catapp.data.network

import com.example.catapp.common.Constants
import com.example.catapp.data.models.BreedDetailModel
import com.example.catapp.data.models.CatBreedDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {

    @GET(Constants.BREEDS)
    suspend fun getCatBreed(): List<CatBreedDataModel>

    @GET(Constants.API_CAT_END_POINT)
    suspend fun getBreedDetails(
        @Query(Constants.QUERY_ID) id: String
    ): List<BreedDetailModel>
}