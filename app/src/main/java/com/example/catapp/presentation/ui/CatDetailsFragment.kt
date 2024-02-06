package com.example.catapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.catapp.R
import com.example.catapp.data.models.BreedDetailModel
import com.example.catapp.databinding.FragmentCatDetailsBinding
import com.example.catapp.presentation.viewmodel.CatViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CatDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCatDetailsBinding
    private val viewModel: CatViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.sharedCatBreed.value?.id?.let {
            viewModel.getBreedDetailsData(it)
            setupDataObserver()
        }
    }

    private fun setupDataObserver() {
        viewModel.breedDetailsData.observe(viewLifecycleOwner) { breedList ->
            breedList.getOrNull(0)?.let { setupUI(it) }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(this@CatDetailsFragment.context, it, Toast.LENGTH_SHORT).show()
            binding.pbLoader.visibility = View.GONE
        }
    }

    private fun setupUI(breedDetailModel: BreedDetailModel) {
        loadImage(breedDetailModel.url)
        viewModel.sharedCatBreed.value?.let {
            binding.apply {
                tvBreedName.text = resources.getString(R.string.breed_name, it.name)
                tvOrigin.text = resources.getString(R.string.origin, it.origin)
                tvDescription.text = resources.getString(R.string.description, it.description)
                tvLifeSpan.text = resources.getString(R.string.life_span, it.lifeSpan)
                pbLoader.visibility = View.GONE
            }
        }
    }

    private fun loadImage(imgUrl: String?) {
        imgUrl?.let {
            Glide.with(this)
                .load(it)
                .placeholder(R.drawable.image_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(binding.ivCat)
        }
    }

    override fun onDestroy() {
        viewModel.clearBreedDetails()
        super.onDestroy()
    }
}