package com.example.noticias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.noticias.ui.theme.NoticiasTheme
import android.content.Intent
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.example.noticias.uinoticias.NewsListScreen
import com.example.noticias.viewmodel.NewsViewModel


class MainActivity : ComponentActivity() {
    private val viewModel = NewsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val newsList by viewModel.newsList

            LaunchedEffect(Unit) {
                viewModel.fetchNews("2fgxePp4o7CwnjIwwpEdD8Aayvp0e4D9")
            }

            NewsListScreen(newsList = newsList) { news ->
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("NEWS_TITLE", news.title)
                    putExtra("NEWS_DESCRIPTION", news.abstract)
                    putExtra("NEWS_IMAGE", news.multimedia?.firstOrNull()?.url)
                }
                startActivity(intent)
            }
        }
    }
}
