package com.anggadps.mymoviedb

import com.anggadps.mymoviedb.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/now_playing?language=en-US&page=1")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String = RetrofitClient.getApiKey()): Call<MovieResponse>
}
