package com.example.catapp.presentation.adapter.listener

import com.example.catapp.data.models.CatBreedDataModel

interface CatItemClickListener {
    fun onCatItemClick(catDetails: CatBreedDataModel)
}