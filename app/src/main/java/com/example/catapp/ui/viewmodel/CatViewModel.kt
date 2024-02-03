package com.example.catapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.BreedDetailModel
import com.example.catapp.data.CatBreedDataModel
import com.example.catapp.data.network.CatApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val client: CatApiService
): ViewModel() {
     val catBreedData = MutableLiveData<List<CatBreedDataModel>>()
     val breedDetailsData = MutableLiveData<List<BreedDetailModel>>()
     val errorMessage = MutableLiveData<String>()

     fun getCatBreedData() {
         viewModelScope.launch {
             try {
                 val response = client.getCatBreed()
                 catBreedData.postValue(response)
             } catch (e: Exception) {
                 errorMessage.postValue(e.message)
             }
         }
    }

     fun getBreedDetailsData(id: String) {
         viewModelScope.launch {
             try {
                 val response = client.getBreedDetails(id)
                 breedDetailsData.postValue(response)
             } catch (e: Exception) {
                 errorMessage.postValue(e.message)
             }
         }
    }
}