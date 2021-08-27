package com.skanderjabouzi.rvezytest.network

import com.skanderjabouzi.rvezytest.model.Breed
import com.skanderjabouzi.rvezytest.model.BreedX
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatApi {
    @Headers("x-api-key: 24be637f-e596-4847-b47a-1791feeea1bd")
    @GET("search")
    suspend fun getBreeds(@Query("limit") limit: Int, @Query("page") page: Int): List<Breed>

    @Headers("x-api-key: 24be637f-e596-4847-b47a-1791feeea1bd")
    @GET("search")
    suspend fun getBreed(@Query("breed-id") name: String): List<BreedX>
}