package com.fatihbaserpl.appcentnewsapp.api

import com.fatihbaserpl.appcentnewsapp.domain.NewsResponse
import com.fatihbaserpl.appcentnewsapp.util.Constants.API_KEY
import com.fatihbaserpl.appcentnewsapp.util.Constants.PAGE_SIZE
import com.fatihbaserpl.appcentnewsapp.util.Constants.SORT_BY

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {

    @GET("v2/top-headlines")
    suspend fun getAllNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("pageSize") pageSize: Int = PAGE_SIZE,
        @Query("sortBy") sortBy: String = SORT_BY,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") searchQuery: String,
        @Query("pageSize") pageSize: Int = PAGE_SIZE,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>
}