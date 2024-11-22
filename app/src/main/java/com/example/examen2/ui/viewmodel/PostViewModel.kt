package com.example.examen2.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen2.data.local.PostDatabase
import com.example.examen2.data.repository.PostRepository
import kotlinx.coroutines.launch



class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val postDao = PostDatabase.getDatabase(application).postDao()
    private val postRepository = PostRepository(postDao)

    fun fetchPosts() {
        viewModelScope.launch {
            postRepository.fetchPostsFromApi()
        }
    }

    fun getPosts() {
        viewModelScope.launch {
            val posts = postRepository.getPostsFromDatabase()
        }
    }
}