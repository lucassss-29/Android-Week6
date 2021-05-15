package com.thesis.week6.Movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thesis.week6.Movie.rest.RestClient
import kotlinx.coroutines.launch

class MovieViewModel:ViewModel() {

    fun getData(){
        viewModelScope.launch {
            RestClient.getInstance().API.listNowPlayMovies(language = "en-US",page = 1, apiKey = "b4ae39a7e05c4636498b82ddcb9656d4")
        }
    }
}