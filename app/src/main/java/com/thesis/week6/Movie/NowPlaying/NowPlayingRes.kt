package com.thesis.week6.Movie.NowPlaying

data class NowPlayingRes (
    val dates: Dates,
    val page: Long,
    val results: List<NowPlayingResult>,
    val totalPages: Long,
    val totalResults: Long
)
data class Dates (
    val maximum: String,
    val minimum: String
)