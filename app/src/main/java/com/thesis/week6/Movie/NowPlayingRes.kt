package com.thesis.week6.Movie

data class NowPlayingRes (
    val dates: Dates? = null ,
    val page: Long? = null ,
    val results: List<NowPlayingResult>? = null ,
    val totalPages: Long? = null ,
    val totalResults: Long? = null
    )
data class Dates (
    val maximum: String? = null ,
    val minimum: String? = null
)