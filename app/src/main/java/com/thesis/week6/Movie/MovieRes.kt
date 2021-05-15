package com.thesis.week6.Movie

data class MovieRes (
    val dates: Dates,
    val page: Long,
    val results: List<Movie>,
    val totalPages: Long,
    val totalResults: Long
)

data class Dates (
    val maximum: String,
    val minimum: String
)



enum class OriginalLanguage {
    En,
    Ja,
    Ko,
    Ru
}
