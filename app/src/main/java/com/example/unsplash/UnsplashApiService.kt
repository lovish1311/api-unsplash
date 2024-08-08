package com.example.unsplash

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UnsplashApiService {
    @GET("photos/random")
    fun getRandomPhoto(
        @Header("Authorization") authorization: String
    ): Call<List<UnsplashPhoto>>
}