package com.anggadps.mymoviedb.services

import com.anggadps.mymoviedb.RetrofitClient
import com.anggadps.mymoviedb.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/now_playing?language=en-US&page=1")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String = RetrofitClient.getApiKey()): Call<MovieResponse>


    @GET("movie/top_rated?language=en-US&page=1")
    fun getTopRatedMovies(@Query("api_key") apiKey: String = RetrofitClient.getApiKey()): Call<MovieResponse>

    @GET("movie/upcoming?language=en-US&page=1")
    fun getUpcomingMovies(@Query("api_key") apiKey: String = RetrofitClient.getApiKey()):Call<MovieResponse>

}
