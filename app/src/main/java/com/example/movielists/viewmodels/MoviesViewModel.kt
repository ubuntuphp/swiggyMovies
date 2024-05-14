package com.example.movielists.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielists.ApiRepo.MoviesApiRepo
import com.example.movielists.MoviesModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val apiInterface = MoviesApiRepo.getMoviesApi()
    val state : MutableState<MoviesModel> = mutableStateOf(MoviesModel())

    fun fetchMovies(s:String){
        viewModelScope.launch {
           val result = apiInterface.getMovies(s)
            state.value = result
        }
    }
}