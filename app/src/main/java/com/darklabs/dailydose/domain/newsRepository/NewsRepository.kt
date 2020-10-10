package com.darklabs.dailydose.domain.newsRepository

import com.darklabs.businessdomain.db.database.dao.NewsArticleDao
import com.darklabs.businessdomain.db.database.entity.NewsArticleEntity
import com.darklabs.businessdomain.network.api.NewsApi
import com.darklabs.businessdomain.network.model.NewsResponse
import com.darklabs.businessdomain.network.util.safeApiCall
import com.darklabs.dailydose.util.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


interface NewsRepository {

    fun getNewsTopHeadlines(country: String): Flow<ViewState<List<NewsArticleEntity>>>

    suspend fun getHeadLinesFromServer(country: String): Response<NewsResponse>
}

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val newsDao: NewsArticleDao,
    private val newsService: NewsApi
) : NewsRepository, NewsMapper {

    override fun getNewsTopHeadlines(country: String): Flow<ViewState<List<NewsArticleEntity>>> =
        flow {
            emit(ViewState.loading())

            val freshNews = getHeadLinesFromServer(country)

            freshNews.body()?.articles?.toStorage()?.let(newsDao::clearAndCacheArticles)

            val cachedNews = newsDao.getNewsArticles()

            emitAll(cachedNews.map { ViewState.success(it) })
        }
            .flowOn(Dispatchers.IO)

    override suspend fun getHeadLinesFromServer(country: String): Response<NewsResponse> {
        return safeApiCall { newsService.getTopHeadLines(country) }
    }

}