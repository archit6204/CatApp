package com.example.catapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.catapp.R
import com.example.catapp.common.Constants
import com.example.catapp.data.models.BreedDetailModel
import com.example.catapp.databinding.FragmentCatDetailsBinding
import com.example.catapp.presentation.viewmodel.CatViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CatDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCatDetailsBinding
    private val viewModel: CatViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(Constants.ID) ?: ""
        val name = arguments?.getString(Constants.NAME) ?: ""
        val lifeSpan = arguments?.getString(Constants.LIFESPAN) ?: ""
        val description = arguments?.getString(Constants.DESCRIPTION) ?: ""
        val origin = arguments?.getString(Constants.ORIGIN) ?: ""
        setupViewModel(id, name, description, origin, lifeSpan)
    }

    private fun setupViewModel(
        id: String,
        name: String,
        description: String,
        origin: String,
        lifeSpan: String
    ) {
        viewModel.getBreedDetailsData(id = id)
        setupDataObserver(name, description, origin, lifeSpan)
    }

    private fun setupDataObserver(
        name: String,
        description: String,
        origin: String,
        lifeSpan: String
    ) {
        viewModel.breedDetailsData.observe(viewLifecycleOwner) {
            setupUI(it[0], name, description, origin, lifeSpan)
        }
        viewModel.errorMessageBreedDetails.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            binding.pbLoader.visibility = View.GONE
        }
    }

    private fun setupUI(
        breedDetailModel: BreedDetailModel,
        name: String,
        description: String,
        origin: String,
        lifeSpan: String
    ) {
        loadImage(breedDetailModel.url)
        binding.run {
            tvBreedName.text = resources.getString(R.string.breed_name, name)
            tvOrigin.text = resources.getString(R.string.origin, origin)
            tvDescription.text = resources.getString(R.string.description, description)
            tvLifeSpan.text = resources.getString(R.string.life_span, lifeSpan)
            pbLoader.visibility = View.GONE
        }
    }

    private fun loadImage(imgUrl: String?) {
        imgUrl?.let {
            Glide.with(this)
                .load(it)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(binding.ivCat)
        }
    }

}