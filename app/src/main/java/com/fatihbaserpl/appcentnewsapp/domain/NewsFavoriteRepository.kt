package com.fatihbaserpl.appcentnewsapp.domain

import androidx.room.Insert
import com.fatihbaserpl.appcentnewsapp.favoriteroom.ArticleDao
import com.fatihbaserpl.appcentnewsapp.remote.NewsInterface
import javax.inject.Inject

class NewsFavoriteRepository @Inject constructor(private val articleDao: ArticleDao) {


    suspend fun addFavorite(article: Article){
        articleDao.upsert(article)
    }

    suspend fun deleteFavorite(article: Article){
        articleDao.deleteArticle(article)
    }


    fun getAll() =articleDao.getAllArticles()



}