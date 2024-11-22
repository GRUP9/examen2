package com.example.examen2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.examen2.data.model.PostEntity


@Dao
interface PostDao {


    @Insert
    suspend fun insertPost(postEntity: PostEntity)


    @Query("SELECT * FROM posts")
    suspend fun getAllPosts(): List<PostEntity>


    @Query("SELECT * FROM posts WHERE id = :id")
    suspend fun getPostById(id: Int): PostEntity?
}