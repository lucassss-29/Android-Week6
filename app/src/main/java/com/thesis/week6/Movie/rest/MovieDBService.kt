package com.thesis.week6.Movie.rest

import com.thesis.week6.Movie.NowPlaying.NowPlayingRes
import com.thesis.week6.Movie.TopRated.TopRatedRes
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBService {

    @GET("movie/top_rated")
    suspend fun listTopRatedMovies(
        @Query ("language") language: String,
        @Query ("page") page: Int
    ):TopRatedRes

    @GET("movie/now_playing")
    suspend fun listNowPlayingMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ):NowPlayingRes
}