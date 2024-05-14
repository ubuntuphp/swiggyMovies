package com.example.movielists

import com.example.movielists.models.MovieDetailModel
import com.google.gson.annotations.SerializedName


data class MoviesModel (

  @SerializedName("Search"       ) var search       : ArrayList<MovieDetailModel> = arrayListOf(),

)