package com.thesis.week6.Movie.rest

import com.thesis.week6.Movie.Movie
import com.thesis.week6.Movie.MovieRes
import com.thesis.week6.Movie.TopRatedRes
import com.thesis.week6.Movie.TopRatedResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBService {

    @GET("movie/top_rated")
    suspend fun listNowPlayMovies(
        @Query ("api_key") apiKey: String,
        @Query ("language") language: String,
        @Query ("page") page: Int
    ):MovieRes
}