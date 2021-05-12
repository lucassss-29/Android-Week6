package com.thesis.week6.Movie.rest

import com.thesis.week6.Movie.NowPlayingRes
import com.thesis.week6.Movie.TopRatedRes
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBService {

    @GET("movie/now_playing")
    suspend fun listNowPlayMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): NowPlayingRes

}