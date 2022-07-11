package com.fatihbaserpl.appcentnewsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatihbaserpl.appcentnewsapp.domain.Article
import com.fatihbaserpl.appcentnewsapp.repository.NewsFavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(

    val newsRepository: NewsFavoriteRepository
) : ViewModel() {


    fun AddFavorite(article: Article) = viewModelScope.launch {
        newsRepository.addFavorite(article)
    }

    fun getSavedNews() = newsRepository.getAll()


    fun deleteFavorite(article: Article) = viewModelScope.launch {
        newsRepository.deleteFavorite(article)
    }


}