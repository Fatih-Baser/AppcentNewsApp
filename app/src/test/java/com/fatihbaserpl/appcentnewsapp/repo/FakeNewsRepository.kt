package com.fatihbaserpl.appcentnewsapp.repo

import androidx.lifecycle.MutableLiveData
import com.fatihbaserpl.appcentnewsapp.domain.Article
import com.fatihbaserpl.appcentnewsapp.domain.NewsRepository
import com.fatihbaserpl.appcentnewsapp.domain.NewsResponse
import com.fatihbaserpl.appcentnewsapp.util.Resource


class FakeNewsRepository : NewsRepository {

    private val article = mutableListOf<Article>()
    private val articleLiveData = MutableLiveData<List<Article>>(article)


    override suspend fun getNews(country: String, category: String): Resource<NewsResponse> {
        return Resource.Success(NewsResponse(listOf(), 0))
    }

    override suspend fun searchNews(searchQuery: String): Resource<NewsResponse> {
        return Resource.Success(NewsResponse(listOf(), 0))
    }
}