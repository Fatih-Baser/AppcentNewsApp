package com.fatihbaserpl.appcentnewsapp.repository

import com.fatihbaserpl.appcentnewsapp.domain.Article
import com.fatihbaserpl.appcentnewsapp.room.ArticleDao
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