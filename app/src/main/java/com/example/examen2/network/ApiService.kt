package com.example.examen2.network

import retrofit2.Call
import retrofit2.http.GET
import com.example.examen2.data.model.Post
import com.example.examen2.data.model.PostEntity
import retrofit2.Response


interface ApiService {
    @GET("posts")
    fun getPosts(): Response<List<PostEntity>>
}