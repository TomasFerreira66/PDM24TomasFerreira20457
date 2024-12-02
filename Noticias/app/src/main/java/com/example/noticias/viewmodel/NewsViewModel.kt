package com.example.noticias.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noticias.api.RetrofitInstance
import com.example.noticias.model.News
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class NewsViewModel : ViewModel() {
    private val _newsList = mutableStateOf<List<News>>(emptyList())
    val newsList: State<List<News>> = _newsList

    fun fetchNews(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTopStories(apiKey)
                _newsList.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
