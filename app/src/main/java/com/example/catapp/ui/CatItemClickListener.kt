package com.example.catapp.ui

interface CatItemClickListener {
    fun onCatItemClick(id: String, name: String, description: String, origin: String, lifeSpan: String)
}