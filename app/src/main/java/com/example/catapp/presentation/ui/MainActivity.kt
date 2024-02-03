package com.example.catapp.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.R
import com.example.catapp.common.Constants
import com.example.catapp.data.models.CatBreedDataModel
import com.example.catapp.databinding.ActivityMainBinding
import com.example.catapp.presentation.adapter.listener.CatItemClickListener
import com.example.catapp.presentation.adapter.CatAdapter
import com.example.catapp.presentation.viewmodel.CatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CatItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var catAdapter: CatAdapter
    private val viewModel: CatViewModel by viewModels()
    private var name = ""
    private var description = ""
    private var lifeSpan = ""
    private var origin = ""

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            handleOnBackPress()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setupViewModel()
        getCatBreedData()
        setContentView(view)
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun getCatBreedData() {
        viewModel.getCatBreedData()
    }

    private fun setupViewModel() {
        viewModel.catBreedData.observe(this) {
            setupRecyclerView(it)
            binding.pbLoader.visibility = View.GONE
        }
        viewModel.errorMessageCatBreed.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            binding.pbLoader.visibility = View.GONE
        }
    }

    private fun setupRecyclerView(responseBody: List<CatBreedDataModel>) {
        catAdapter = CatAdapter(this)
        catAdapter.submitList(responseBody)
        binding.rvCat.apply {
            adapter = catAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,
                RecyclerView.VERTICAL, false)
        }
    }

    override fun onCatItemClick(
        id: String,
        name: String,
        description: String,
        origin: String,
        lifeSpan: String
    ) {
        this.name = name
        this.description = description
        this.origin = origin
        this.lifeSpan = lifeSpan
        startFragment(id)
    }

    private fun showView() {
        binding.apply {
            rvCat.visibility = View.VISIBLE
        }
    }

    private fun hideView() {
        binding.apply {
            rvCat.visibility = View.GONE
        }
    }

    private fun startFragment(id: String) {
        val movieDetailsFragment = CatDetailsFragment()
        movieDetailsFragment.arguments = createBundle(id)
        this.supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, movieDetailsFragment)
            .addToBackStack(null)
            .commit()
        hideView()
    }

    private fun createBundle(id: String): Bundle {
        val bundle = Bundle()
        bundle.putString(Constants.ID, id)
        bundle.putString(Constants.NAME, name)
        bundle.putString(Constants.LIFESPAN, lifeSpan)
        bundle.putString(Constants.DESCRIPTION, description)
        bundle.putString(Constants.ORIGIN, origin)

        return bundle
    }

    private fun handleOnBackPress() {
        val count = supportFragmentManager.backStackEntryCount
        if (count != 0) {
            supportFragmentManager.popBackStack()
            showView()
        }
    }
}