package com.skanderjabouzi.rvezytest.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private val baseUrl = "https://api.thecatapi.com/v1/images/"

    fun getRetrofit(): Retrofit {
        val okhttpClient = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        okhttpClient.addInterceptor(logging)

        val gson = GsonBuilder()
            .setLenient()
            .create()
        val gsonFactory = GsonConverterFactory.create(gson)

        okhttpClient.addInterceptor(logging)


        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonFactory)
            .client(okhttpClient.build())
            .build()
    }

}