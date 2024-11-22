package com.example.examen2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.examen2.ui.composables.PostList
import com.example.examen2.ui.viewmodel.PostViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            PostList()
        }


        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)


        obtenerPosts()
    }

    private fun obtenerPosts() {

        lifecycleScope.launch {
            try {
                val posts = postViewModel.fetchPosts()
                posts?.forEach {
                    println("Post: ${it.title}")
                }
            } catch (e: Exception) {

                Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

