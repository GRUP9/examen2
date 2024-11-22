package com.example.examen2.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.examen2.data.model.Post

@Composable
fun PostList(posts: List<Post>) {
    Column {
        posts.forEach { post ->
            Text(text = post.title)
        }
    }
}
