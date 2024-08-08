package com.example.unsplash

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {
    @GET("photos/random?client_id=pYXWJmjZJGeLK4YFxjjZrFx7EfhsxvZjfE1MEux7xp0")
    fun getRandomPhoto(): Call<UnsplashPhoto>
}