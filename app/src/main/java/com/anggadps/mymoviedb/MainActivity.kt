// File: MainActivity.kt
package com.anggadps.mymoviedb

import android.content.Intent
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity(), MovieItemClickListener {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var topRatedAdapter: MovieAdapter
    private lateinit var upcomingAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize views
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)

        // Set refresh listener
        swipeRefreshLayout.setOnRefreshListener {
            // Refresh action
            fetchNowPlayingMovies()
            fetchTopRatedMovies()
            fetchUpcomingMovies()
        }

        movieAdapter = MovieAdapter(this, isUpcoming = false)
        binding.recyclerViewMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewMovies.adapter = movieAdapter

        topRatedAdapter = MovieAdapter(this, isUpcoming = false)
        binding.recyclerViewTopRatedMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewTopRatedMovies.adapter = topRatedAdapter

        upcomingAdapter = MovieAdapter(this, isUpcoming = true)
        binding.recyclerViewUpcomingMovies.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewUpcomingMovies.adapter = upcomingAdapter



        fetchNowPlayingMovies()
        fetchTopRatedMovies()
        fetchUpcomingMovies()
    }

    private fun fetchNowPlayingMovies() {
        val call = RetrofitClient.movieApiService.getNowPlayingMovies()
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    swipeRefreshLayout.isRefreshing = false
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

    private fun fetchTopRatedMovies() {
        val call = RetrofitClient.movieApiService.getTopRatedMovies()
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    swipeRefreshLayout.isRefreshing = false
                    movies?.let {
                        topRatedAdapter.submitList(it)
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun fetchUpcomingMovies() {
        val call = RetrofitClient.movieApiService.getUpcomingMovies()
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    swipeRefreshLayout.isRefreshing = false
                    movies?.let {
                        upcomingAdapter.submitList(it)
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }


    override fun onMovieItemClick(movieDataList: Movie) {
        Log.i("=================", "=================")
        Log.d("Movie Clicked", movieDataList.title)
        Log.d("Movie Id", movieDataList.id.toString())
        Log.i("=================", "=================")


        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movieBackdrop", movieDataList.backdrop_path)
        intent.putExtra("movieTitle", movieDataList.title)
        intent.putExtra("movieOverview", movieDataList.overview)
        startActivity(intent)
    }
}
