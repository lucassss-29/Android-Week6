package com.thesis.week6.Movie

data class TopRatedRes (
    val page: Long,
    val results: List<TopRatedResult>,
    val totalPages: Long,
    val totalResults: Long
)
