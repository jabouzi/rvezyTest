package com.skanderjabouzi.rvezytest.presentation

import com.skanderjabouzi.rvezytest.network.CatRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skanderjabouzi.rvezytest.model.Breed
import kotlinx.coroutines.launch

class BreedsViewModel(private val catRepository: CatRepository): ViewModel() {
    private val _breeds = MutableLiveData<List<Breed>?>()
    val breeds: LiveData<List<Breed>?>
        get() = _breeds

    private val _status = MutableLiveData<CatStatus>()
    val status: LiveData<CatStatus>
        get() = _status

    var limit = 10
    var page = 1

    init {
        getReposList(limit, page)
    }

    fun getReposList(limit: Int, page: Int) {
        viewModelScope.launch {
            _status.value = CatStatus.LOADING
            try {
                _breeds.value = catRepository.getBreeds(
                    limit,
                    page
                )
                _status.value = CatStatus.DONE
            } catch (e: Exception) {
                _breeds.value = null
                _status.value = CatStatus.ERROR
            }
        }
    }


}