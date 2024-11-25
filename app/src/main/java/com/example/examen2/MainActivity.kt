package com.example.examen2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.examen2.data.api.RetrofitInstance
import com.example.examen2.data.db.PostDatabase
import com.example.examen2.data.repository.PostRepository
import com.example.examen2.ui.PostListScreen
import com.example.examen2.viewmodel.PostViewModel
import com.example.examen2.viewmodel.PostViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val db = Room.databaseBuilder(applicationContext, PostDatabase::class.java, "post_db")
            .fallbackToDestructiveMigration()
            .build()


        val repository = PostRepository(RetrofitInstance.api, db.postDao())


        val viewModelFactory = PostViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)

        setContent {

            PostListScreen(viewModel)
        }
    }
}


