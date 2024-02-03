package com.example.catapp.presentation.adapter.listener

interface CatItemClickListener {
    fun onCatItemClick(
        id: String,
        name: String,
        description: String,
        origin: String,
        lifeSpan: String
    )
}