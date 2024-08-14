package com.example.unsplash

data class UnsplashPhoto(
    val id:String,
    val slug:String,
    val urls: Urls
)

data class Urls(
    val regular: String
)
data class Post(val userId: Int,
                val id: Int,
                val title: String,
                val body: String)
data class studentDetails(
    val phoneNumber: String?,
    val rollNo: String?
)