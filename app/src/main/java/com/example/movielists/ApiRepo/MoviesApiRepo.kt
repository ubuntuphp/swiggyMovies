package com.example.movielists.ApiRepo

import com.example.movielists.API_BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesApiRepo {
    val moviesApiRepo by lazy {
        Retrofit.Builder().addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(API_BASE_URL).build()
    }

    fun getMoviesApi():ApiInterface {
        return moviesApiRepo.create(ApiInterface::class.java)
    }
}