package com.example.examen2.data.repository

import com.example.examen2.data.local.PostDao
import com.example.examen2.data.model.PostEntity
import com.example.examen2.network.RetrofitClient
import com.example.examen2.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostRepository(private val postDao: PostDao) {

    private val apiService: ApiService = RetrofitClient.apiService


    suspend fun fetchPostsFromApi() {

        val response: Response<List<PostEntity>> = apiService.getPosts()

        if (response.isSuccessful) {
            response.body()?.let { posts ->
                savePostsToDatabase(posts)
            }
        }
    }

    private suspend fun savePostsToDatabase(posts: List<PostEntity>) {
        withContext(Dispatchers.IO) {
            posts.forEach { post ->
                postDao.insertPost(post)
            }
        }
    }

    suspend fun getPostsFromDatabase(): List<PostEntity> {
        return withContext(Dispatchers.IO) {
            postDao.getAllPosts()
        }
    }

    suspend fun getPostByIdFromDatabase(id: Int): PostEntity? {
        return withContext(Dispatchers.IO) {
            postDao.getPostById(id)
        }
    }
}