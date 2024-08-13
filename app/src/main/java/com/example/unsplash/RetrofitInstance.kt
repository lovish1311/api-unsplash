package com.example.unsplash
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.unsplash.com/"
    private const val BASE_URL_JSONPLACEHOLDER = "https://jsonplaceholder.typicode.com/"

    val api: UnsplashApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashApiService::class.java)
    }
    val jsonPlaceholderInstance: UnsplashApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_JSONPLACEHOLDER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashApiService::class.java)
    }
}