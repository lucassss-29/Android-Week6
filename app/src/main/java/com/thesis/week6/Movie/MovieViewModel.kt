package com.thesis.week6.Movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.thesis.week6.Movie.rest.RestClient
import com.thesis.week6.Restaurant.Restaurant
import com.thesis.week6.UserInfo.User
import com.thesis.week6.databinding.ActivityMovieBinding
import kotlinx.coroutines.launch

class MovieViewModel():ViewModel() {
    val listdata = MutableLiveData<List<NowPlayingResult>>()
    var data = MutableLiveData<NowPlayingResult>()
    //init {
   //     data.value = NowPlayingResult()
   // }
    fun getNowPlaying() {
        viewModelScope.launch {
            val movieResp = RestClient.getInstance().API.listNowPlayMovies(
                language = "en-US",
                page = 1,
                apiKey = "b4ae39a7e05c4636498b82ddcb9656d4"
            )
            listdata.value = movieResp.results
        }
    }

    fun setdataNowPlaying(Mov:NowPlayingResult){
        data.postValue(Mov)
    }

}





/* fun getNowPlayingData() : List<NowPlayingResult>{
     Log.e("Tag--------------",data.toString())
     return data
 }*/
