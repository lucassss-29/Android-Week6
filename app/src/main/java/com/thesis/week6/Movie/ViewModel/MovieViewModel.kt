package com.thesis.week6.Movie.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thesis.week6.Movie.NowPlaying.NowPlayingFragment
import com.thesis.week6.Movie.NowPlaying.NowPlayingResult
import com.thesis.week6.Movie.TopRated.TopRatedRes
import com.thesis.week6.Movie.TopRated.TopRatedResult
import com.thesis.week6.Movie.rest.RestClient
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {

    val listTopRatedData = MutableLiveData<List<TopRatedResult>>()
    val listNowPlayingData = MutableLiveData<List<NowPlayingResult>>()
    var topRatedData = MutableLiveData<TopRatedResult>()
    var nowPlayingData = MutableLiveData<NowPlayingResult>()

    fun getTopRatedData(){
        viewModelScope.launch {
            val MovieResp = RestClient.getInstance().API.listTopRatedMovies("en-US", 1)
            Log.e("TAG", MovieResp.results.toString())
            listTopRatedData.value = MovieResp.results
        }

    }

    fun setDataTopRated(Mov: TopRatedResult){
        topRatedData.postValue(Mov)
    }

    fun getNowPlayingData(){
        viewModelScope.launch {
            val NowPlayingResp = RestClient.getInstance().API.listNowPlayingMovies("en-US", 1)
            Log.e("NOW PLAYING TAG", NowPlayingResp.results.toString())
            listNowPlayingData.value = NowPlayingResp.results
        }
    }

    fun setDataNowPlaying(nMov: NowPlayingResult){
        nowPlayingData.postValue(nMov)
    }
}