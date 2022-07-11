package com.fatihbaserpl.appcentnewsapp.repository


import com.fatihbaserpl.appcentnewsapp.domain.NewsResponse
import com.fatihbaserpl.appcentnewsapp.api.NewsInterface
import com.fatihbaserpl.appcentnewsapp.util.Resource
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsInterface: NewsInterface) :
    NewsRepository {
    override suspend fun getNews(country: String, category: String): Resource<NewsResponse> {
        return try {
            val response = newsInterface.getAllNews(country, category)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("Error", null)
            } else {
                Resource.Error("Error", null)
            }
        } catch (e: Exception) {
            Resource.Error("No data!", null)
        }
    }


    override suspend fun searchNews(searchQuery: String): Resource<NewsResponse> {
        return try {
            val response = newsInterface.searchForNews(searchQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("Error", null)
            } else {
                Resource.Error("Error", null)
            }
        } catch (e: Exception) {
            Resource.Error("No data!", null)
        }
    }
}