package com.example.unsplash


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var idTextView: TextView
    private lateinit var slugTextView: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        idTextView = findViewById(R.id.textView)
        slugTextView = findViewById(R.id.textView2)
        imageView = findViewById(R.id.imageView)

        fetchRandomPhoto()
    }

    private val clientId = "YOUR_ACCESS_KEY"
    private fun fetchRandomPhoto() {
        // Fetch the photo from Unsplash API
        val authorizationHeader = "Client-ID $clientId" // Format the authorization header

        RetrofitInstance.api.getRandomPhoto(authorizationHeader)
            .enqueue(object : Callback<List<UnsplashPhoto>> {
                override fun onResponse(
                    call: Call<List<UnsplashPhoto>>,
                    response: Response<List<UnsplashPhoto>>
                ) {
                    if (response.isSuccessful) {
                        val photo = response.body()?.firstOrNull()
                        photo?.let {
                            // Update UI with photo details
                            idTextView.text = "ID: ${it.id}"
                            slugTextView.text = "Slug: ${it.slug}"
                            Glide.with(this@MainActivity)
                                .load(it.urls.regular)
                                .into(imageView)
                        }
                    }
                }

                override fun onFailure(call: Call<List<UnsplashPhoto>>, t: Throwable) {
                    // Handle error
                    t.printStackTrace()
                }
            })
    }
}