package com.fatihbaserpl.appcentnewsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatihbaserpl.appcentnewsapp.repository.NewsRepository
import com.fatihbaserpl.appcentnewsapp.domain.NewsResponse
import com.fatihbaserpl.appcentnewsapp.util.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: NewsRepository) :
    ViewModel() {

    private val _response = MutableLiveData<Resource<NewsResponse>>()
    val newsResponse: LiveData<Resource<NewsResponse>>
        get() = _response

    init {
        getBreakingNews()
    }

    private fun getBreakingNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getNews(country = "tr", category = "business")
            _response.postValue(result)
        }
    }

    fun searchNews(searchQuery: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchNews(searchQuery)
            _response.postValue(result)
        }
    }

}