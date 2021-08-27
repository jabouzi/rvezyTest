package com.skanderjabouzi.rvezytest.network

import com.skanderjabouzi.rvezytest.model.Breed
import com.skanderjabouzi.rvezytest.model.BreedX
import retrofit2.Retrofit

class RetrofitClient(retrofit: Retrofit) {
    private val catApi: CatApi = retrofit.create(CatApi::class.java)

    suspend fun getBreeds(limit: Int, page: Int) : List<Breed> {
        return catApi.getBreeds(limit, page)
    }

    suspend fun getBreed(id: String) : List<BreedX> {
        return catApi.getBreed(id)
    }
}