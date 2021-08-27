package com.skanderjabouzi.rvezytest.network

import com.skanderjabouzi.rvezytest.model.Breed
import com.skanderjabouzi.rvezytest.model.BreedX

class CatRepository() {

    private val retrofitClient = RetrofitClient(Network.getRetrofit())

    suspend fun getBreeds(limit: Int, page: Int) : List<Breed> {
        return retrofitClient.getBreeds(limit, page)
    }

    suspend fun getBreed(id: String) : List<BreedX> {
        return retrofitClient.getBreed(id)
    }
}