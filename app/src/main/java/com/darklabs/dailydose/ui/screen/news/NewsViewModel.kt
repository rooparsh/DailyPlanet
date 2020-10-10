package com.darklabs.dailydose.ui.screen.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.darklabs.businessdomain.db.database.entity.NewsArticleEntity
import com.darklabs.dailydose.base.BaseViewModel
import com.darklabs.dailydose.domain.newsRepository.NewsRepository
import com.darklabs.dailydose.util.ViewState

class NewsViewModel @ViewModelInject constructor(newsRepository: NewsRepository) : BaseViewModel() {

    private val _newsArticle: LiveData<ViewState<List<NewsArticleEntity>>> =
        newsRepository.getNewsTopHeadlines("in").asLiveData()
    val newsArticle = _newsArticle


}