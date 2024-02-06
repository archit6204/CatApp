package com.example.catapp.domain

import com.example.catapp.data.models.CatBreedDataModel
import com.example.catapp.data.network.CatApiService
import javax.inject.Inject

class CatBreedDataUseCase @Inject constructor(
    private val client: CatApiService
) {

    suspend fun getCatBreedData(): List<CatBreedDataModel> {
        return client.getCatBreed()
    }

}