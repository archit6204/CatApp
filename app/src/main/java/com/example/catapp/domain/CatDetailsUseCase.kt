package com.example.catapp.domain

import com.example.catapp.data.models.BreedDetailModel
import com.example.catapp.data.network.CatApiService
import javax.inject.Inject

class CatDetailsUseCase @Inject constructor(
    private val client: CatApiService
) {

    suspend fun getBreedDetailsData(id: String): List<BreedDetailModel> {
        return client.getBreedDetails(id)
    }

}