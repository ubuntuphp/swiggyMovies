package com.example.movielists

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.movielists.models.MovieDetailModel
import com.example.movielists.ui.theme.MovieListsTheme
import com.example.movielists.viewmodels.MoviesViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MoviesViewModel
    private var pageNo = mutableIntStateOf(0)
    private var selectedMovieData: MovieDetailModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        enableEdgeToEdge()
        setContent {
            MainView()
        }
        viewModel.fetchMovies("")
    }

    @Composable
    fun MainView() {
        if(pageNo.intValue == 0)MoviesListView()
        else MoviesDetailsView(selectedMovieData)
    }

    @Composable
    fun MoviesListView() {
        Column(modifier = Modifier.padding(20.dp)) {
            var search by remember { mutableStateOf("") }
            TextField(value = search, onValueChange = {
                search = it
                viewModel.fetchMovies(search)},
                modifier = Modifier.padding(20.dp),
            )
            LazyColumn {
                items(items = viewModel.state.value.search){
                    MoviesListItem(data = it)
                }
            }
        }
    }
    
    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun MoviesListItem(data:MovieDetailModel){
        Column(modifier = Modifier
            .clickable {
                showMovieDetails(data)
            }
            .padding(10.dp)) {
            Row{
                GlideImage(model = data.Poster, contentDescription = "")
                Text(text = data.Title ?: "")
            }
            Text(text = data.imdbID ?: "")

        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun MoviesDetailsView(data: MovieDetailModel?) {
        if(data == null)return
        Column(modifier = Modifier.padding(top = 100.dp)) {
            Text(text = data.Title)
            Text(text = data.imdbID)
            Text(text = data.Year)
            Text(text = data.Type)
            GlideImage(model = data.Poster, contentDescription = "")
        }
    }

    private fun showMovieDetails(data: MovieDetailModel){
        pageNo.intValue = 1
        selectedMovieData = data
    }

    private fun showMovieList(){
        pageNo.intValue = 0
    }

    override fun onBackPressed() {
        if(pageNo.intValue == 1){
            showMovieList()
        }else{
            super.onBackPressed()
        }
    }
}