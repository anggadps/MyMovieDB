package com.anggadps.mymoviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.anggadps.mymoviedb.models.Movie
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movieBackdrop = intent.getStringExtra("movieBackdrop")
        val movieTitle = intent.getStringExtra("movieTitle")
        val movieOverview = intent.getStringExtra("movieOverview")
        Log.d("DetailActivity", "Movie Title: $movieTitle")

        val imgBackdrop = findViewById<ImageView>(R.id.imageViewPoster)
        val txtTitle = findViewById<TextView>(R.id.textViewTitle)
        val txtOverview = findViewById<TextView>(R.id.textViewOverview)

        if (movieBackdrop != null) {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${movieBackdrop}")
                .into(imgBackdrop)
        }
        txtTitle.text = movieTitle
        txtOverview.text = movieOverview
    }
}