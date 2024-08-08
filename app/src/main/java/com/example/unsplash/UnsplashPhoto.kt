package com.example.unsplash

data class UnsplashPhoto(
    val id:String,
    val slug:String,
    val urls: Urls
)

data class Urls(
    val regular: String
)