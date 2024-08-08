package com.example.unsplash

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var idTextView: TextView
    private lateinit var slugTextView: TextView
    private lateinit var imageView:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        idTextView = findViewById(R.id.textView)
        slugTextView=findViewById(R.id.textView2)
        imageView=findViewById(R.id.imageView)

        fetchRandomPhoto()
    }

    private fun fetchRandomPhoto() {
        RetrofitInstance.api.getRandomPhoto().enqueue(object : Callback<UnsplashPhoto> {
            override fun onResponse(
                call: Call<UnsplashPhoto>,
                response: Response<UnsplashPhoto>
            ) {
                if (response.isSuccessful) {
                    val apiData = response.body()
                    if (apiData != null) {
                        // Get the ID of the random photo
                        val Id = apiData.id
                        val slugId = apiData.slug
                        val photourl = apiData.urls.regular
                        idTextView.text = "Photo ID: $Id"
                        slugTextView.text="slug: $slugId"
                        Glide.with(this@MainActivity)
                            .load(photourl)
                            .into(imageView)
                    } else {
                        idTextView.text = "No photo data available"
                        slugTextView.text = "No photo data available"
                        imageView.setImageResource(R.drawable.ic_launcher_background)
                    }
                } else {
                    idTextView.text = "Error: ${response.message()}"
                    slugTextView.text = "Error: ${response.message()}"
                    imageView.setImageResource(R.drawable.ic_launcher_foreground)


                }
            }

            override fun onFailure(call: Call<UnsplashPhoto>, t: Throwable) {
                idTextView.text = "Error: ${t.message}"
                slugTextView.text = "Error: ${t.message}"
            }
        })
    }
}