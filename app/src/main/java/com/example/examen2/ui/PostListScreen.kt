package com.example.examen2.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.examen2.viewmodel.PostViewModel

@Composable
fun PostListScreen(viewModel: PostViewModel) {
    val posts by viewModel.posts.collectAsState(initial = emptyList())

    Column {
        Button(onClick = { viewModel.fetchPostsFromApi() }) {
            Text("Actualizar desde la API")
        }

        LazyColumn {
            items(posts) { post ->
                PostItem(post)
            }
        }
    }
}



