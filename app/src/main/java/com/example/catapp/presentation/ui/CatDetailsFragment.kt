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
        setupViewModel(getBundleData(Constants.ID))
    }

    private fun setupViewModel(id: String) {
        viewModel.getBreedDetailsData(id = id)
        setupDataObserver()
    }

    private fun setupDataObserver() {
        viewModel.breedDetailsData.observe(viewLifecycleOwner) {
            setupUI(it[0])
        }
        viewModel.errorMessageBreedDetails.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            binding.pbLoader.visibility = View.GONE
        }
    }

    private fun setupUI(breedDetailModel: BreedDetailModel) {
        loadImage(breedDetailModel.url)
        binding.apply {
            tvBreedName.text = resources.getString(R.string.breed_name, getBundleData(Constants.NAME))
            tvOrigin.text = resources.getString(R.string.origin, getBundleData(Constants.ORIGIN))
            tvDescription.text = resources.getString(R.string.description, getBundleData(Constants.DESCRIPTION))
            tvLifeSpan.text = resources.getString(R.string.life_span, getBundleData(Constants.LIFESPAN))
            pbLoader.visibility = View.GONE
        }
    }

    private fun getBundleData(key: String): String {
        return arguments?.getString(key) ?: ""
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