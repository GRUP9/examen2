package com.example.examen2.data.api

import com.example.examen2.data.db.PostEntity
import retrofit2.Response
import retrofit2.http.GET
import com.example.examen2.data.model.Post

interface ApiService {
    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>
}