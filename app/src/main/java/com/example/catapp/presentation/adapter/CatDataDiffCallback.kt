package com.example.catapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.catapp.data.models.CatBreedDataModel

class CatDataDiffCallback: DiffUtil.ItemCallback<CatBreedDataModel>() {
    override fun areItemsTheSame(oldItem: CatBreedDataModel, newItem: CatBreedDataModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: CatBreedDataModel,
        newItem: CatBreedDataModel
    ): Boolean {
        return oldItem.id == newItem.id
    }
}