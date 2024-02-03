package com.example.catapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.catapp.data.models.CatBreedDataModel
import com.example.catapp.databinding.ItemCatBinding
import com.example.catapp.presentation.adapter.listener.CatItemClickListener
import com.example.catapp.presentation.adapter.viewholder.CatAdapterViewHolder

class CatAdapter(
    private val catItemClickListener: CatItemClickListener
) : ListAdapter<CatBreedDataModel, CatAdapterViewHolder>(CatDataDiffCallback()) {

    private lateinit var catBinding: ItemCatBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatAdapterViewHolder {
        val binding = ItemCatBinding.inflate(
                LayoutInflater.from(parent.context),
            parent,
        false
        )
        catBinding = binding
        return CatAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatAdapterViewHolder, position: Int) {
        val cat = getItem(position)
        catBinding.tvCatBreedName.text = cat.name
        cat.id?.let {
            handleCatItemClick(
                it,
                cat.name.toString(),
                cat.description.toString(),
                cat.origin.toString(),
                cat.lifeSpan.toString()
            )
        }

    }

    private fun handleCatItemClick(
        imdbId: String,
        name: String,
        description: String,
        origin: String,
        lifeSpan: String
    ) {
        catBinding.root.setOnClickListener {
            catItemClickListener.onCatItemClick(imdbId, name, description, origin, lifeSpan)
        }
    }
}
