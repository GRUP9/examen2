package com.example.examen2.data.model

import com.squareup.moshi.Json

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)