package com.example.catapp.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.catapp.data.CatBreedDataModel

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