package com.fatihbaserpl.appcentnewsapp.repository

import com.fatihbaserpl.appcentnewsapp.domain.NewsResponse
import com.fatihbaserpl.appcentnewsapp.util.Resource


interface NewsRepository {

    suspend fun getNews(country: String, category: String): Resource<NewsResponse>
    suspend fun searchNews(searchQuery: String): Resource<NewsResponse>



}