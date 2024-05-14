package com.example.movielists.ApiRepo

import com.example.movielists.API_KEY
import com.example.movielists.MoviesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/")
    suspend fun getMovies(@Query("s")search:String,
                  @Query("apikey") apiKey:String = API_KEY,
                  @Query("page") page:Int = 1
                  ):MoviesModel
}