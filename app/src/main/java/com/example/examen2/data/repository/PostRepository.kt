package com.example.examen2.data.repository

import androidx.lifecycle.LiveData
import com.example.examen2.data.api.ApiService
import com.example.examen2.data.db.PostDao
import com.example.examen2.data.db.PostEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository(
    private val api: ApiService,
    private val postDao: PostDao
) {

    suspend fun fetchPostsFromApiAndStore(): List<PostEntity> {

        val response = api.getPosts()

        if (response.isSuccessful) {
            val posts = response.body() ?: emptyList()


            val postEntities = posts.map { PostEntity(it.id, it.userId, it.title, it.body) }


            withContext(Dispatchers.IO) {
                postDao.insertPosts(postEntities)
            }
            return postEntities
        } else {
            return emptyList()
        }
    }

    fun getPostsFromDb(): Flow<List<PostEntity>> = postDao.getAllPosts()
}

