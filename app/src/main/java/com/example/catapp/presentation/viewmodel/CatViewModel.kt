package com.example.catapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.models.BreedDetailModel
import com.example.catapp.data.models.CatBreedDataModel
import com.example.catapp.data.network.CatApiService
import com.example.catapp.domain.CatBreedDataUseCase
import com.example.catapp.domain.CatDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val catBreedDataUseCase: CatBreedDataUseCase,
    private val catDetailsUseCase: CatDetailsUseCase
): ViewModel() {

    private val _catBreedData = MutableLiveData<List<CatBreedDataModel>>()
    val catBreedData : LiveData<List<CatBreedDataModel>> = _catBreedData

    private val _breedDetailsData = MutableLiveData<List<BreedDetailModel>>()
    val breedDetailsData : LiveData<List<BreedDetailModel>> = _breedDetailsData

    private val _sharedCatBreed = MutableLiveData<CatBreedDataModel>()
    val sharedCatBreed : LiveData<CatBreedDataModel> = _sharedCatBreed

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage


     fun getCatBreedData() {
         viewModelScope.launch {
             try {
                 val response = catBreedDataUseCase.getCatBreedData()
                 _catBreedData.postValue(response)
             } catch (e: Exception) {
                 _errorMessage.postValue(e.message)
             }
         }
    }

     fun getBreedDetailsData(id: String) {
         viewModelScope.launch {
             try {
                 val response = catDetailsUseCase.getBreedDetailsData(id)
                 _breedDetailsData.postValue(response)
             } catch (e: Exception) {
                 _errorMessage.postValue(e.message)
             }
         }
    }

    fun setCatBread(breedDetailModel: CatBreedDataModel) {
        _sharedCatBreed.postValue(breedDetailModel)
    }

    fun clearBreedDetails() {
        _breedDetailsData.postValue(emptyList())
    }
}