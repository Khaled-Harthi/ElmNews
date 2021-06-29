package com.example.elmnewsapp.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.elmnewsapp.data.model.News
import com.example.elmnewsapp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private lateinit var _newsFlow : Flow<PagingData<News>>
    val news : Flow<PagingData<News>> get() = _newsFlow

    private val _selectedNews = MutableLiveData<News>()
    val selectedNews : LiveData<News> = _selectedNews

    fun newsSelected(news : News){
        _selectedNews.value = news
    }


    init {
        getNews()
    }


    private fun getNews() = viewModelScope.launch {
        _newsFlow = repository.getNews().cachedIn(viewModelScope)
    }
}