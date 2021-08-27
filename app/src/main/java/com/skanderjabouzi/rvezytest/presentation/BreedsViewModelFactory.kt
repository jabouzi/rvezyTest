package com.skanderjabouzi.rvezytest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skanderjabouzi.rvezytest.network.CatRepository

class BreedsViewModelFactory(private val catRepository: CatRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = BreedsViewModel(catRepository) as T
}