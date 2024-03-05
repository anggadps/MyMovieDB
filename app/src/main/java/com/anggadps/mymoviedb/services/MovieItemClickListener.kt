package com.anggadps.mymoviedb.services

import com.anggadps.mymoviedb.models.Movie

interface MovieItemClickListener {
    fun onMovieItemClick(movie: Movie)
}