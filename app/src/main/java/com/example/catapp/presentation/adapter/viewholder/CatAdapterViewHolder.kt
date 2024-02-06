package com.example.catapp.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.data.models.CatBreedDataModel
import com.example.catapp.databinding.ItemCatBinding
import com.example.catapp.presentation.adapter.listener.CatItemClickListener

class CatAdapterViewHolder(
    private val binding: ItemCatBinding,
    private val catItemClickListener: CatItemClickListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(catDetails: CatBreedDataModel) {
        binding.tvCatBreedName.text = catDetails.name
        handleCatItemClick(catDetails)
    }

    private fun handleCatItemClick(catDetails: CatBreedDataModel) {
        binding.root.setOnClickListener {
            catItemClickListener.onCatItemClick(catDetails)
        }
    }
}