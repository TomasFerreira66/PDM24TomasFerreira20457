package com.example.noticias.uinoticias

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.noticias.model.News
import androidx.compose.ui.layout.ContentScale

@Composable
fun NewsListScreen(newsList: List<News>, onNewsClick: (News) -> Unit) {
    LazyColumn {
        items(newsList) { news ->
            NewsItem(news = news, onClick = { onNewsClick(news) })
        }
    }
}

@Composable
fun NewsItem(news: News, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = news.title, style = MaterialTheme.typography.titleMedium)
            news.multimedia?.firstOrNull()?.url?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
