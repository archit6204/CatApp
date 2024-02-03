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

    override fun onCatItemClick(catDetails: CatBreedDataModel) {
        startFragment(createBundle(catDetails))
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

    private fun startFragment(bundle: Bundle) {
        val movieDetailsFragment = CatDetailsFragment()
        movieDetailsFragment.arguments = bundle
        this.supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, movieDetailsFragment)
            .addToBackStack(null)
            .commit()

        hideView()
    }

    private fun createBundle(catDetails: CatBreedDataModel): Bundle {
        val bundle = Bundle()
        bundle.apply {
            putString(Constants.ID, catDetails.id)
            putString(Constants.NAME, catDetails.name)
            putString(Constants.LIFESPAN, catDetails.lifeSpan)
            putString(Constants.DESCRIPTION, catDetails.description)
            putString(Constants.ORIGIN, catDetails.origin)
        }

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