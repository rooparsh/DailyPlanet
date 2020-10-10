package com.darklabs.businessdomain.db.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.darklabs.businessdomain.db.database.entity.NewsArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsArticleDao {

    @Insert
    fun insertArticles(articles: List<NewsArticleEntity>): List<Long>

    @Query("DELETE FROM news_article")
    fun clearAllArticles()

    @Transaction
    fun clearAndCacheArticles(articles: List<NewsArticleEntity>) {
        clearAllArticles()
        insertArticles(articles)
    }

    @Query("SELECT * FROM news_article")
    fun getNewsArticles(): Flow<List<NewsArticleEntity>>
}