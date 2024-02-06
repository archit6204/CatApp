package com.example.catapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.catapp.data.models.BreedDetailModel
import com.example.catapp.data.models.CatBreedDataModel
import com.example.catapp.domain.CatBreedDataUseCase
import com.example.catapp.domain.CatDetailsUseCase
import com.example.catapp.presentation.viewmodel.CatViewModel
import io.mockk.coEvery
import io.mockk.mockk
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
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CatViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private var catDetailsUseCase: CatDetailsUseCase = mockk()

    private var catBreedDataUseCase: CatBreedDataUseCase = mockk()

    private lateinit var viewModel: CatViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CatViewModel(catBreedDataUseCase, catDetailsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getCatBreedData_success() = runTest {
        val catBreedData = listOf(CatBreedDataModel("Persian", "Long hair", "Fluffy"))
        coEvery {
            catBreedDataUseCase.getCatBreedData()
        } returns catBreedData

         viewModel.getCatBreedData()
        assertEquals(catBreedData, viewModel.catBreedData.value)
    }

    @Test
    fun getCatBreedData_error() = runTest {
        val errorMessage = "Something Went Wrong!"
        coEvery {
            catBreedDataUseCase.getCatBreedData()
        } throws RuntimeException(errorMessage)

        viewModel.getCatBreedData()
        assertEquals(errorMessage, viewModel.errorMessage.value)
    }

    @Test
    fun getBreedDetailsData_success() = runTest {
        val breedDetailsData = listOf(BreedDetailModel("1","Persian"))
        val breedId = "1"
        coEvery {
            catDetailsUseCase.getBreedDetailsData(breedId)
        } returns breedDetailsData


        viewModel.getBreedDetailsData(breedId)
        assertEquals(breedDetailsData, viewModel.breedDetailsData.value)
    }

    @Test
    fun getBreedDetailsData_error() = runTest {
        val errorMessage = "Something Went Wrong!"
        val breedId = "persian"
        coEvery {
            catDetailsUseCase.getBreedDetailsData(breedId)
        } throws RuntimeException(errorMessage)

        viewModel.getBreedDetailsData(breedId)
        assertEquals(errorMessage, viewModel.errorMessage.value)
    }
}