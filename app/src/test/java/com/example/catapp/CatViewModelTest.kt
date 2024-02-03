package com.example.catapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.catapp.data.BreedDetailModel
import com.example.catapp.data.CatBreedDataModel
import com.example.catapp.data.network.CatApiService
import com.example.catapp.ui.viewmodel.CatViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CatViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var client: CatApiService

    private lateinit var viewModel: CatViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CatViewModel(client)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getCatBreedData_success() = runTest {
        val catBreedData = listOf(CatBreedDataModel("Persian", "Long hair", "Fluffy"))
        `when`(client.getCatBreed()).thenReturn(catBreedData)

        viewModel.getCatBreedData()
        assertEquals(catBreedData, viewModel.catBreedData.value)
    }

    @Test
    fun getCatBreedData_error() = runTest {
        val errorMessage = "Something Went Wrong!"
        `when`(client.getCatBreed()).thenThrow(RuntimeException(errorMessage))

        viewModel.getCatBreedData()
        assertEquals(errorMessage, viewModel.errorMessage.value)
    }

    @Test
    fun getBreedDetailsData_success() = runTest {
        val breedDetailsData = listOf(BreedDetailModel("1","Persian"))
        val breedId = "1"
        `when`(client.getBreedDetails(breedId)).thenReturn(breedDetailsData)

        viewModel.getBreedDetailsData(breedId)
        assertEquals(breedDetailsData, viewModel.breedDetailsData.value)
    }

    @Test
    fun getBreedDetailsData_error() = runTest {
        val errorMessage = "Something Went Wrong!"
        val breedId = "persian"
        `when`(client.getBreedDetails(breedId)).thenThrow(RuntimeException(errorMessage))

        viewModel.getBreedDetailsData(breedId)
        assertEquals(errorMessage, viewModel.errorMessage.value)
    }
}