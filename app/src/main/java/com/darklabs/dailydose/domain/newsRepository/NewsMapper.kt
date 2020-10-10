package com.darklabs.dailydose.domain.newsRepository

import com.darklabs.businessdomain.db.database.entity.NewsArticleEntity
import com.darklabs.businessdomain.network.model.NewsArticle
import com.darklabs.businessdomain.util.Mapper

interface NewsMapper : Mapper<NewsArticleEntity, NewsArticle> {

    override fun NewsArticleEntity.toRemote(): NewsArticle = NewsArticle(
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        source = NewsArticle.Source(source.id, source.name)
    )

    override fun NewsArticle.toStorage(): NewsArticleEntity = NewsArticleEntity(
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        source = NewsArticleEntity.Source(source.id, source.name)
    )
}