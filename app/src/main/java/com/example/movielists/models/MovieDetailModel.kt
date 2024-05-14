package com.example.movielists.models

import com.google.gson.annotations.SerializedName


data class MovieDetailModel (

  @SerializedName("Title"  ) var Title  : String,
  @SerializedName("Year"   ) var Year   : String,
  @SerializedName("imdbID" ) var imdbID : String,
  @SerializedName("Type"   ) var Type   : String,
  @SerializedName("Poster" ) var Poster : String,

)