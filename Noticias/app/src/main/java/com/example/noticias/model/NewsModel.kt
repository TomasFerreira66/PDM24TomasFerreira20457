package com.example.noticias.model

data class NytResponse(
    val results: List<News>
)

data class News(
    val title: String,
    val abstract: String,
    val url: String,
    val multimedia: List<Multimedia>?
)

data class Multimedia(
    val url: String
)
