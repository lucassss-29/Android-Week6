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
                page = 1
            )
            Log.e("TAG", movieResp.results.toString())
        }
    }
}