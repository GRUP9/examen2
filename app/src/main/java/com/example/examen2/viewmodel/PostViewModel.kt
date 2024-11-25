package com.example.examen2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen2.data.db.PostEntity
import com.example.examen2.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



class PostViewModel(private val repository: PostRepository) : ViewModel() {

    private val _posts = MutableStateFlow<List<PostEntity>>(emptyList())
    val posts: StateFlow<List<PostEntity>> get() = _posts

    init {
        fetchPostsFromDb()
    }

    fun fetchPostsFromDb() {
        viewModelScope.launch {
            repository.getPostsFromDb().collect { list ->
                _posts.value = list
            }
        }
    }

    fun fetchPostsFromApi() {
        viewModelScope.launch {
            val postsFromApi = repository.fetchPostsFromApiAndStore()
            _posts.value = postsFromApi
        }
    }
}



