package com.darklabs.dailydose.ui.screen.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.darklabs.businessdomain.db.database.entity.NewsArticleEntity
import com.darklabs.dailydose.base.BaseViewModel
import com.darklabs.dailydose.domain.newsRepository.NewsRepository
import com.darklabs.dailydose.util.ViewState
import com.darklabs.dailydose.util.getCountries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor(private val newsRepository: NewsRepository) :
        BaseViewModel() {


    private var _newsArticle = MutableLiveData<ViewState<List<NewsArticleEntity>>>()
    val newsArticle = _newsArticle as LiveData<ViewState<List<NewsArticleEntity>>>

    private var _countriesMap = MutableLiveData<Map<String, String>>()
    val countries = _countriesMap as LiveData<Map<String, String>>

    fun fetchNewsHeadLines(countryName: String, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = newsRepository.getNewsTopHeadlines(countryName, page)
            data.collect { _newsArticle.postValue(it) }
        }
    }

    fun fetchCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            _countriesMap.postValue(getCountries())
        }
    }

}