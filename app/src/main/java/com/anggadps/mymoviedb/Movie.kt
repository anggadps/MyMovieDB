package com.anggadps.mymoviedb

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String
)

data class MovieResponse(
    val results: List<Movie>
)

