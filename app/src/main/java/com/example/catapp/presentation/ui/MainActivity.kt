package com.example.catapp.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catapp.R
import com.example.catapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        startFragment()
    }

    private fun startFragment() {
        val catBreedFragment = CatBreedFragment()
        this.supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, catBreedFragment)
            .commit()
    }
}