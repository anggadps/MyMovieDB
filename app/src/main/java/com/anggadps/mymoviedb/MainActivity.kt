// File: MainActivity.kt
package com.anggadps.mymoviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggadps.mymoviedb.adapters.MovieAdapter
import com.anggadps.mymoviedb.databinding.ActivityMainBinding
import com.anggadps.mymoviedb.models.Movie
import com.anggadps.mymoviedb.models.MovieResponse
import com.anggadps.mymoviedb.services.MovieItemClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), MovieItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieAdapter = MovieAdapter(this)
        binding.recyclerViewMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewMovies.adapter = movieAdapter

        fetchNowPlayingMovies()
    }

    private fun fetchNowPlayingMovies() {
        val call = RetrofitClient.movieApiService.getNowPlayingMovies()
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    movies?.let {
                        movieAdapter.submitList(it)
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }

    override fun onMovieItemClick(movie: Movie) {
        Log.i("=================", "=================")
        Log.d("Movie Clicked", movie.title)
        Log.d("Movie Id", movie.id.toString())
        Log.i("=================", "=================")
        // Example: You can start DetailActivity here and pass movie data using Intent
    }
}
