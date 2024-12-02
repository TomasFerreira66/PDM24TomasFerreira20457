package com.example.noticias.api

import com.example.noticias.model.NytResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NytApiService {
    @GET("topstories/v2/home.json")
    suspend fun getTopStories(@Query("api-key") apiKey: String): NytResponse
}
