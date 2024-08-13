package com.example.unsplash

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity() {

    private lateinit var textView:TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        textView=findViewById(R.id.textView5)

        val createPostButton: Button = findViewById(R.id.createPostButton)
        createPostButton.setOnClickListener {
            createNewPost()
        }
    }

    private fun createNewPost() {
        val newPost = Post(userId = 1, id = 12, title = "Deftsoft", body = "Informatics")

        RetrofitInstance.jsonPlaceholderInstance.createPost(newPost).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    // Handle the response here, e.g., update UI
                   textView.text="Post created: $post"
                } else {
                    // Handle the error response
                  textView.text= "Error: ${response.errorBody()}"
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                // Handle the failure
               textView.text= "Failed: ${t.message}"
            }
        })
    }
}