package com.anggadps.mymoviedb

import com.anggadps.mymoviedb.services.MovieApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "#"
    fun getApiKey(): String {
        return API_KEY
    }

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieApiService: MovieApiService = retrofit.create(MovieApiService::class.java)
}
