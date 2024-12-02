package com.example.noticias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.noticias.uinoticias.NewsDetailScreen

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val title = intent.getStringExtra("NEWS_TITLE") ?: ""
            val description = intent.getStringExtra("NEWS_DESCRIPTION") ?: ""
            val imageUrl = intent.getStringExtra("NEWS_IMAGE")

            NewsDetailScreen(title = title, description = description, imageUrl = imageUrl)
        }
    }
}
