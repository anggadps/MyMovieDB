package com.anggadps.mymoviedb.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String,
    val release_date: String,
    val vote_average: String,
) : Parcelable


data class MovieResponse(
    val results: List<Movie>
)

