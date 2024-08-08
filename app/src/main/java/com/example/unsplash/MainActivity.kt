package com.example.unsplash

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        fetchRandomPhoto()
    }

    private fun fetchRandomPhoto() {
        RetrofitInstance.api.getRandomPhoto().enqueue(object : Callback<UnsplashPhoto> {
            override fun onResponse(
                call: Call<UnsplashPhoto>,
                response: Response<UnsplashPhoto>
            ) {
                if (response.isSuccessful) {
                    val photo = response.body()
                    if (photo != null) {
                        // Get the ID of the random photo
                        val photoId = photo.id
                        textView.text = "Photo ID: $photoId"
                    } else {
                        textView.text = "No photo data available"
                    }
                } else {
                    textView.text = "Error: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<UnsplashPhoto>, t: Throwable) {
                textView.text = "Error: ${t.message}"
            }
        })
    }
}