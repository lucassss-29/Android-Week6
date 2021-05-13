package com.thesis.week6.Movie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thesis.week6.Movie.rest.RestClient
import kotlinx.coroutines.launch

class MovieViewModel:ViewModel() {

    fun getNowPlaying() {
        viewModelScope.launch {
            val movieResp = RestClient.getInstance().API.listNowPlayMovies(
                language = "en-US",
                page = 1,
                apiKey = "b4ae39a7e05c4636498b82ddcb9656d4"
            )
            Log.e("TAG", movieResp.results.toString())
        }
    }
}