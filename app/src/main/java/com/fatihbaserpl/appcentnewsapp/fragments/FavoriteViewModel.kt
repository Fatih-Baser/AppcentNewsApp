package com.fatihbaserpl.appcentnewsapp.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatihbaserpl.appcentnewsapp.domain.Article
import com.fatihbaserpl.appcentnewsapp.domain.NewsFavoriteRepository
import com.fatihbaserpl.appcentnewsapp.domain.NewsResponse
import com.fatihbaserpl.appcentnewsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(

    val newsRepository : NewsFavoriteRepository
) : ViewModel(){



    fun AddFavorite(article: Article) = viewModelScope.launch {
        newsRepository.addFavorite(article)
    }
    fun getSavedNews() = newsRepository.getAll()




    fun deleteFavorite(article: Article) = viewModelScope.launch {
        newsRepository.deleteFavorite(article)
    }





}