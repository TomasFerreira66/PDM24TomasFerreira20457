package com.example.noticias.uinoticias

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale

@Composable
fun NewsDetailScreen(title: String, description: String, imageUrl: String?) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        imageUrl?.let {
            AsyncImage(
                model = it,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = description, style = MaterialTheme.typography.bodyMedium)
    }
}
