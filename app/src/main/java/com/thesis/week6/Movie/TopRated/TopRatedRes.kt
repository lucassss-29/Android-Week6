package com.thesis.week6.Movie.TopRated

data class TopRatedRes (
    val page: Long,
    val results: List<TopRatedResult>,
    val totalPages: Long,
    val totalResults: Long
)