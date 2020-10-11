package com.darklabs.dailydose.ui.screen.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.darklabs.businessdomain.db.database.entity.NewsArticleEntity
import com.darklabs.dailydose.base.BaseViewModel
import com.darklabs.dailydose.domain.newsRepository.NewsRepository
import com.darklabs.dailydose.util.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor(private val newsRepository: NewsRepository) :
        BaseViewModel() {


    private var _newsArticle = MutableLiveData<ViewState<List<NewsArticleEntity>>>()
    val newsArticle = _newsArticle as LiveData<ViewState<List<NewsArticleEntity>>>

    fun fetchNewsHeadLines(countryName: String, page: Int) {
        viewModelScope.launch {
            val data = with(Dispatchers.IO) {
                newsRepository.getNewsTopHeadlines(countryName, page)
            }

            data.collect { _newsArticle.value = it }
        }
    }

}